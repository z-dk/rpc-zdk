package com.zdk.rpc.core.exception;

/**
 * @author zdk
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3365624081242234230L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
