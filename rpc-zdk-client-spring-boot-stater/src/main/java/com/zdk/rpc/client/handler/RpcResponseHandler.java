package com.zdk.rpc.client.handler;

import com.zdk.rpc.client.cache.LocalRpcResponseCache;
import com.zdk.rpc.core.common.RpcResponse;
import com.zdk.rpc.core.protocol.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 *  数据响应处理器
 * @author zdk
 * @date 2021年12月5日
 */
@Slf4j
public class RpcResponseHandler extends SimpleChannelInboundHandler<MessageProtocol<RpcResponse>> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, 
                                MessageProtocol<RpcResponse> rpcResponseMessageProtocol) {
        String requestId = rpcResponseMessageProtocol.getHeader().getRequestId();
        // 收到响应 设置响应数据
        LocalRpcResponseCache.fillResponse(requestId, rpcResponseMessageProtocol);
    }
}
