package com.zdk.rpc.server.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 暴露服务注解
 * @author zdk 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface RpcService {

    /**
     * 暴露服务接口类型
     * @return 接口类型
     */
    Class<?> interfaceType() default Object.class;

    /**
     * 服务版本
     * @return 版本号
     */
    String version() default "1.0";
}
