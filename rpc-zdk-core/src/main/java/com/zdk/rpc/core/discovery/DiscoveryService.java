package com.zdk.rpc.core.discovery;

import com.zdk.rpc.core.common.ServiceInfo;

/**
 * 服务发现
 * @date 2021年12月5日
 * @author zdk
 */
public interface DiscoveryService {

    /**
     * 发现
     * @param serviceName 服务名
     * @return 服务
     * @throws Exception 异常
     */
    ServiceInfo discovery(String serviceName) throws Exception;

}
