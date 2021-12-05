package com.zdk.rpc.consumer.controller;

import com.zdk.rpc.client.annotation.RpcAutowired;
import com.zdk.rpc.provider.MessageProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>类 名 称</b> :  MessageProviderController<br/>
 * <b>类 描 述</b> :  controller<br/>
 * <b>创 建 人</b> :  zhudengkui<br/>
 * <b>创建时间</b> :  2021/12/5 20:50<br/>
 * <b>修 改 人</b> :  zhudengkui<br/>
 * <b>修改时间</b> :  2021/12/5 20:50<br/>
 * <b>修改备注</b> :  <br/>
 *
 * @author zdk
 */
@RestController
@RequestMapping("message")
public class MessageProviderController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProviderController.class);
    
    @RpcAutowired(version = "1.0")
    MessageProviderService messageProviderService;
    
    @RequestMapping("connection")
    public String getConnection(String name) {
        LOGGER.info("开始建立连接,name={}", name);
        return messageProviderService.getConnection(name);
    }
}
