package com.kakaopay.coupon.error.exception;

public class NotExistCouponException extends RuntimeException {

    public static final String errorCode = "not.exist.coupon";

    public NotExistCouponException(String message) {
        super(message);
    }

    public NotExistCouponException(String message, Throwable cause) {
        super(message, cause);
    }
}
