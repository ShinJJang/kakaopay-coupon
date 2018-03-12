package com.kakaopay.coupon.error;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorInfo {
    private String uri;
    private String msg;
    private String errorCode;

    public ErrorInfo(String uri, String msg, String errorCode) {
        this.uri = uri;
        this.msg = msg;
        this.errorCode = errorCode;
    }
}
