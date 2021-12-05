package com.zdk.rpc.core.serialization;

import lombok.Getter;

/**
 * @author zdk
 * @date 2021年12月5日
 */
public enum SerializationTypeEnum {

    /**
     * hessian
     */
    HESSIAN((byte) 0),

    /**
     * json
     */
    JSON((byte) 1);

    @Getter
    private final byte type;

    SerializationTypeEnum(byte type) {
        this.type = type;
    }

    public static SerializationTypeEnum parseByName(String typeName) {
        for (SerializationTypeEnum typeEnum : SerializationTypeEnum.values()) {
            if (typeEnum.name().equalsIgnoreCase(typeName)) {
                return typeEnum;
            }
        }
        return HESSIAN;
    }

    public static SerializationTypeEnum parseByType(byte type) {
        for (SerializationTypeEnum typeEnum : SerializationTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum;
            }
        }
        return HESSIAN;
    }

}
