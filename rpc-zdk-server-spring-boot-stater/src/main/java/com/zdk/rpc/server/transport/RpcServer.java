package com.zdk.rpc.server.transport;

/**
 * @author zdk
 */
public interface RpcServer {

    /**
     * 开启服务
     * @param port 端口号
     */
    void start(int port);

}
