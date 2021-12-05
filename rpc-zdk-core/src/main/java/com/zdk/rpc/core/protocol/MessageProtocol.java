package com.zdk.rpc.core.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息协议
 * @date 2021年12月5日
 * @author zdk
 */
@Data
public class MessageProtocol<T> implements Serializable {

    /**
     *  消息头
     */
    private MessageHeader header;

    /**
     *  消息体
     */
    private T body;

}
