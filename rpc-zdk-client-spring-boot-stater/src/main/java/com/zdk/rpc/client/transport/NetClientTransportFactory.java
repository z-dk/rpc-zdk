package com.zdk.rpc.client.transport;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zdk
 * @date 2021/7/25 15:12
 */
@Slf4j
public class NetClientTransportFactory {

    public static NetClientTransport getNetClientTransport(){
        return new NettyNetClientTransport();
    }


}
