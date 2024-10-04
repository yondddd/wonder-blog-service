package com.wish.common.exception;

/**
 * @Description: 非法请求异常
 * @Author: Yond
 */

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
