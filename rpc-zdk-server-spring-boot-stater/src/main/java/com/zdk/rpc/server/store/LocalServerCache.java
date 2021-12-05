package com.zdk.rpc.server.store;


import java.util.HashMap;
import java.util.Map;

/**
 * 在处理 RPC 请求时可以直接通过 cache 拿到对应的服务进行调用。避免反射实例化服务开销
 * @author zdk 
 */
public final class LocalServerCache {

    private static final Map<String, Object> SERVER_CACHE_MAP = new HashMap<>();

    public static void store(String serverName, Object server) {
        SERVER_CACHE_MAP.merge(serverName, server, (Object oldObj, Object newObj) -> newObj);
    }

    public static Object get(String serverName) {
        return SERVER_CACHE_MAP.get(serverName);
    }

    public static Map<String, Object> getAll(){
        return null;
    }
}
