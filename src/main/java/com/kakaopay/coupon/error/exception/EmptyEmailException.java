package com.kakaopay.coupon.error.exception;

public class EmptyEmailException extends RuntimeException {

    public static final String errorCode = "empty.email";

    public EmptyEmailException(String message) {
        super(message);
    }

    public EmptyEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
