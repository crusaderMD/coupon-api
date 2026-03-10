package com.lbortolotti.coupon.api.exception;

import com.lbortolotti.coupon.api.error.ApiErrorCode;

public class CouponNotFoundException extends BusinessException{

    public CouponNotFoundException(final String code) {
        super(ApiErrorCode.COUPON_NOT_FOUND, "Coupon " + code + " not found");
    }
}
