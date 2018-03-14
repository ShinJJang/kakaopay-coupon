package com.kakaopay.coupon.error.exception;

public class EmptyCodeException extends RuntimeException {

    public static final String errorCode = "empty.code";

    public EmptyCodeException(String message) {
        super(message);
    }

    public EmptyCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
