package com.yond.common.exception;

import java.io.Serial;

/**
 * @Description: 持久化异常
 * @Author: Yond
 */
public class PersistenceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8334760132436983471L;

    public PersistenceException() {
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
