package com.zdk.rpc.core.protocol;

import lombok.Getter;

/**
 * 请求状态
 * @date 2021年12月5日
 * @author zdk 
 */
public enum MsgStatus {

    /**
     * 成功
     */
    SUCCESS((byte)0),

    /**
     * 失败
     */
    FAIL((byte)1);

    @Getter
    private final byte code;

    MsgStatus(byte code) {
        this.code = code;
    }

    public static boolean isSuccess(byte code){
        return MsgStatus.SUCCESS.code == code;
    }

}
