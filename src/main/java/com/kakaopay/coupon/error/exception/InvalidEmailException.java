package com.kakaopay.coupon.error.exception;

public class InvalidEmailException extends RuntimeException {

    public static final String errorCode = "invalid.email";

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
