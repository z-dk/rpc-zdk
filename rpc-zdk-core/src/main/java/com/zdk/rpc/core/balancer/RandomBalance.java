package com.zdk.rpc.core.balancer;

import com.zdk.rpc.core.common.ServiceInfo;

import java.util.List;
import java.util.Random;

/**
 * 随机算法
 * @author zdk
 */
public class RandomBalance implements LoadBalance {

    private static final Random RANDOM = new Random();

    @Override
    public ServiceInfo chooseOne(List<ServiceInfo> services) {
        return services.get(RANDOM.nextInt(services.size()));
    }
}
