package com.lbortolotti.coupon.api.error;

public enum ApiErrorCode {
    COUPON_ALREADY_EXISTS("Coupon already exists"),
    INTERNAL_SERVER_ERROR("Unexpected internal error");

    private final String defaultMessage;

    ApiErrorCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
