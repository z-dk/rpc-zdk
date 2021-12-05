package com.zdk.rpc.server;

import com.zdk.rpc.core.common.ServiceInfo;
import com.zdk.rpc.core.common.ServiceUtil;
import com.zdk.rpc.core.register.RegistryService;
import com.zdk.rpc.server.annotation.RpcService;
import com.zdk.rpc.server.config.RpcServerProperties;
import com.zdk.rpc.server.store.LocalServerCache;
import com.zdk.rpc.server.transport.RpcServer;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;

import java.net.InetAddress;
import java.util.concurrent.*;

/**
 * @author zdk
 */
@Slf4j
public class RpcServerProvider implements BeanPostProcessor, CommandLineRunner {

    private final RegistryService registryService;

    private final RpcServerProperties properties;

    private final RpcServer rpcServer;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 2000L, TimeUnit.MILLISECONDS, 
            new SynchronousQueue<>(false), new DefaultThreadFactory("rpc-server"), 
            new ThreadPoolExecutor.CallerRunsPolicy());

    public RpcServerProvider(RegistryService registryService, RpcServer rpcServer, RpcServerProperties properties) {
        this.registryService = registryService;
        this.properties = properties;
        this.rpcServer = rpcServer;
    }


    /**
     * 所有bean 实例化之后处理
     * <p>
     * 暴露服务注册到注册中心
     * <p>
     * 容器启动后开启netty服务处理请求
     *
     * @param bean spring bean
     * @param beanName bean名称
     * @return 当前bean
     * @throws BeansException 异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
        if (rpcService != null) {
            try {
                String serviceName = rpcService.interfaceType().getName();
                String version = rpcService.version();
                LocalServerCache.store(ServiceUtil.serviceKey(serviceName, version), bean);

                ServiceInfo serviceInfo = new ServiceInfo();
                serviceInfo.setServiceName(ServiceUtil.serviceKey(serviceName, version));
                serviceInfo.setPort(properties.getPort());
                serviceInfo.setAddress(InetAddress.getLocalHost().getHostAddress());
                serviceInfo.setAppName(properties.getAppName());

                // 服务注册
                registryService.register(serviceInfo);
            } catch (Exception ex) {
                log.error("服务注册出错:beanName:{}", beanName, ex);
            }
        }
        return bean;
    }

    /**
     * 启动rpc服务 处理请求
     *
     * @param args 启动参数
     */
    @Override
    public void run(String... args) {
        executor.submit(() -> rpcServer.start(properties.getPort()));
        log.info(" rpc server :{} start, appName :{} , port :{}", rpcServer, properties.getAppName(), properties.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                // 关闭之后把服务从ZK上清楚
                registryService.destroy();
            }catch (Exception ex){
                log.error("", ex);
            }

        }));
    }

}
