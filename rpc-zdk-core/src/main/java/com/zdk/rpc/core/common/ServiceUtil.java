package com.zdk.rpc.core.common;

/**
 * 
 * @author zdk
 */
public class ServiceUtil {

    /**
     * 根据服务名及版本获取服务唯一标识
     * @param serviceName 服务名
     * @param version 服务版本
     * @return 返回服务标识
     */
    public static String serviceKey(String serviceName, String version) {
        return String.join("-", serviceName, version);
    }

}
