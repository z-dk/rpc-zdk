package com.zdk.rpc.core.balancer;

import com.zdk.rpc.core.common.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 轮询算法
 * @author zdk
 */
public class FullRoundBalance implements LoadBalance {

    private static final Logger logger = LoggerFactory.getLogger(FullRoundBalance.class);

    private int index;

    @Override
    public synchronized ServiceInfo chooseOne(List<ServiceInfo> services) {
        // 加锁防止多线程情况下，index超出services.size()
        if (index >= services.size()) {
            logger.debug("已完成一次轮询");
            index = 0;
        }
        return services.get(index++);
    }
}
