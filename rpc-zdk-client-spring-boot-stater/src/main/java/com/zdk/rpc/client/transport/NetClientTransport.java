package com.zdk.rpc.client.transport;

import com.zdk.rpc.core.common.RpcResponse;
import com.zdk.rpc.core.protocol.MessageProtocol;

/**
 * 网络传输层
 * @date 2021年12月5日
 * @author zdk
 */
public interface NetClientTransport {

    /**
     *  发送数据
     * @param metadata 元数据
     * @return 响应
     * @throws Exception 异常
     */
    MessageProtocol<RpcResponse> sendRequest(RequestMetadata metadata) throws Exception;

}
