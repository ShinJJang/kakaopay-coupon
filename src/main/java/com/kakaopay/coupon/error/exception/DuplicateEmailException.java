package com.kakaopay.coupon.error.exception;

public class DuplicateEmailException extends RuntimeException {

    public static final String errorCode = "duplicate.email";

    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
