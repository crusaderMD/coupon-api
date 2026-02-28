package com.lbortolotti.coupon.api.error;

public enum ApiErrorCode {
    COUPON_ALREADY_EXISTS("Coupon already exists");
    private final String defaultMessage;

    ApiErrorCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
