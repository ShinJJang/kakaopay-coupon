package com.kakaopay.coupon.error.exception;

public class CodeCollisionException extends RuntimeException {

    public static final String errorCode = "code.collision";

    public CodeCollisionException(String message) {
        super(message);
    }

    public CodeCollisionException(String message, Throwable cause) {
        super(message, cause);
    }
}
