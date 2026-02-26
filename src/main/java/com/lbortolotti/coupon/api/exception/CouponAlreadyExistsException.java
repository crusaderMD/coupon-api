package com.lbortolotti.coupon.api.exception;

public class CouponAlreadyExistsException extends RuntimeException{

    private final String code;

    public CouponAlreadyExistsException(final String code) {
        super(buildMessage(code));
        this.code = code;
    }

    private static String buildMessage(final String code) {
        return "Coupon with code " + code + " already exists";
    }

    public String getCode() {
        return code;
    }
}
