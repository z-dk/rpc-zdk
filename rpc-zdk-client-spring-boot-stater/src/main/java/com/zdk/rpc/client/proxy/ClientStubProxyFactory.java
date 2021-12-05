package com.zdk.rpc.client.proxy;

import com.zdk.rpc.client.config.RpcClientProperties;
import com.zdk.rpc.core.discovery.DiscoveryService;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zdk 
 */
public class ClientStubProxyFactory {

    private final Map<Class<?>, Object> objectCache = new HashMap<>();

    /**
     * 获取代理对象
     *
     * @param clazz   接口
     * @param version 服务版本
     * @param <T> 对象
     * @return 代理对象
     */
    public <T> T getProxy(Class<T> clazz, String version, DiscoveryService discoveryService, RpcClientProperties properties) {
        return (T) objectCache.computeIfAbsent(clazz, clz ->
                Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, 
                        new ClientStubInvocationHandler(discoveryService, properties, clz, version))
        );
    }
}
