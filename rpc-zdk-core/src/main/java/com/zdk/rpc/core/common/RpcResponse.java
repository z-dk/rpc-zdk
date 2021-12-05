package com.zdk.rpc.core.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zdk
 * @date 2021年12月5日
 */
@Data
public class RpcResponse implements Serializable {

    private Object data;
    private String message;

}
