package com.zdk.rpc.core.register;

import com.zdk.rpc.core.common.ServiceInfo;

import java.io.IOException;

/**
 * 服务注册发现
 * @date 2021/7/23 14:29
 * @author zdk
 */
public interface RegistryService {

    /**
     * 注册服务
     * @param serviceInfo 服务信息
     * @throws Exception 异常
     */
    void register(ServiceInfo serviceInfo) throws Exception;

    /**
     * 注销服务
     * @param serviceInfo 服务信息
     * @throws Exception 异常
     */
    void unRegister(ServiceInfo serviceInfo) throws Exception;

    /**
     * 销毁
     * @throws IOException 异常
     */
    void destroy() throws IOException;

}
