package com.zdk.rpc.provider.service;

import com.zdk.rpc.provider.MessageProviderService;
import com.zdk.rpc.server.annotation.RpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>类 名 称</b> :  MessageProviderServiceImpl<br/>
 * <b>类 描 述</b> :  service<br/>
 * <b>创 建 人</b> :  zhudengkui<br/>
 * <b>创建时间</b> :  2021/12/5 20:33<br/>
 * <b>修 改 人</b> :  zhudengkui<br/>
 * <b>修改时间</b> :  2021/12/5 20:33<br/>
 * <b>修改备注</b> :  <br/>
 *
 * @author zdk
 */
@RpcService(interfaceType = MessageProviderService.class)
public class MessageProviderServiceImpl implements MessageProviderService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProviderServiceImpl.class);
    
    @Override
    public String getConnection(String name) {
        LOGGER.info("建立连接,name={}", name);
        return "hello," + name + "欢迎来到我的世界!";
    }
}
