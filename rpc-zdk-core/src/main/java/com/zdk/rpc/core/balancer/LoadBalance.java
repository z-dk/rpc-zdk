package com.zdk.rpc.core.balancer;

import com.zdk.rpc.core.common.ServiceInfo;

import java.util.List;

/**
 * 负载均衡算法接口
 * @author zdk
 */
public interface LoadBalance {
    
    /**
     * 从服务列表中选择实际提供服务的服务信息
     * @param services 服务列表
     * @return 实际提供服务的服务
     */
    ServiceInfo chooseOne(List<ServiceInfo> services);
}
