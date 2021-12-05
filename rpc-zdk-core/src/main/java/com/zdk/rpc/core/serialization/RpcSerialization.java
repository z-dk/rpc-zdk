package com.zdk.rpc.core.serialization;

import java.io.IOException;

/**
 * @author zdk 
 * @date 2021年12月5日
 */
public interface RpcSerialization {

    /**
     * 序列化
     * @param obj 序列化对象
     * @param <T> 泛型
     * @return 字节
     * @throws IOException io异常
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     * @param data 字节
     * @param clz 类型
     * @param <T> 泛型
     * @return 反序列化后的对象
     * @throws IOException io异常
     */
    <T> T deserialize(byte[] data, Class<T> clz) throws IOException;
}