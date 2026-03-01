package com.lbortolotti.coupon.api.error;

import org.springframework.http.HttpStatus;

public enum ApiErrorCode {
    COUPON_ALREADY_EXISTS(HttpStatus.CONFLICT,"Coupon already exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected internal error"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation error");

    private final HttpStatus httpStatus;
    private final String defaultMessage;

    ApiErrorCode(HttpStatus httpStatus, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
