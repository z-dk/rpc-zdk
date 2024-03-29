package com.zdk.rpc.client.proxy;

import com.zdk.rpc.client.config.RpcClientProperties;
import com.zdk.rpc.client.transport.NetClientTransportFactory;
import com.zdk.rpc.client.transport.RequestMetadata;
import com.zdk.rpc.core.common.RpcRequest;
import com.zdk.rpc.core.common.RpcResponse;
import com.zdk.rpc.core.common.ServiceInfo;
import com.zdk.rpc.core.common.ServiceUtil;
import com.zdk.rpc.core.discovery.DiscoveryService;
import com.zdk.rpc.core.exception.ResourceNotFoundException;
import com.zdk.rpc.core.exception.RpcException;
import com.zdk.rpc.core.protocol.MessageHeader;
import com.zdk.rpc.core.protocol.MessageProtocol;
import com.zdk.rpc.core.protocol.MsgStatus;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zdk
 */
@Slf4j
public class ClientStubInvocationHandler implements InvocationHandler {

    private final DiscoveryService discoveryService;

    private final RpcClientProperties properties;

    private final Class<?> clazz;

    private final String version;

    public ClientStubInvocationHandler(DiscoveryService discoveryService, RpcClientProperties properties, Class<?> clazz, 
                                       String version) {
        super();
        this.clazz = clazz;
        this.version = version;
        this.discoveryService = discoveryService;
        this.properties = properties;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1、获得服务信息
        ServiceInfo serviceInfo = discoveryService.discovery(ServiceUtil.serviceKey(this.clazz.getName(), this.version));
        if (serviceInfo == null) {
            throw new ResourceNotFoundException("404");
        }

        MessageProtocol<RpcRequest> messageProtocol = new MessageProtocol<>();
        // 设置请求头
        messageProtocol.setHeader(MessageHeader.build(properties.getSerialization()));
        // 设置请求体
        RpcRequest request = new RpcRequest();
        request.setServiceName(ServiceUtil.serviceKey(this.clazz.getName(), this.version));
        request.setMethod(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParameters(args);
        messageProtocol.setBody(request);

        // 发送网络请求 拿到结果
        MessageProtocol<RpcResponse> responseMessageProtocol = NetClientTransportFactory.getNetClientTransport()
                .sendRequest(RequestMetadata.builder()
                        .protocol(messageProtocol).address(serviceInfo.getAddress())
                        .port(serviceInfo.getPort()).timeout(properties.getTimeout()).build());

        if (responseMessageProtocol == null) {
            log.error("请求超时");
            throw new RpcException("rpc调用结果失败， 请求超时 timeout:" + properties.getTimeout());
        }

        if (!MsgStatus.isSuccess(responseMessageProtocol.getHeader().getStatus())) {
            log.error("rpc调用结果失败， message：{}", responseMessageProtocol.getBody().getMessage());
            throw new RpcException(responseMessageProtocol.getBody().getMessage());
        }
        return responseMessageProtocol.getBody().getData();
    }
}
