package com.zdk.rpc.client.cache;

import com.zdk.rpc.client.transport.RpcFuture;
import com.zdk.rpc.core.common.RpcResponse;
import com.zdk.rpc.core.protocol.MessageProtocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  请求和响应映射对象
 * @author zdk 
 */
public class LocalRpcResponseCache {

    private static final Map<String, RpcFuture<MessageProtocol<RpcResponse>>> REQUEST_RESPONSE_CACHE = new ConcurrentHashMap<>();

    /**
     *  添加请求和响应的映射关系
     * @param reqId 请求id
     * @param future 响应结果
     */
    public static void add(String reqId, RpcFuture<MessageProtocol<RpcResponse>> future){
        REQUEST_RESPONSE_CACHE.put(reqId, future);
    }

    /**
     *  设置响应数据
     * @param reqId 请求id
     * @param messageProtocol 协议
     */
    public static void fillResponse(String reqId, MessageProtocol<RpcResponse> messageProtocol){
        // 获取缓存中的 future
        RpcFuture<MessageProtocol<RpcResponse>> future = REQUEST_RESPONSE_CACHE.get(reqId);

        // 设置数据
        future.setResponse(messageProtocol);

        // 移除缓存
        REQUEST_RESPONSE_CACHE.remove(reqId);
    }
}
