package com.lbortolotti.coupon.api.exception;

import com.lbortolotti.coupon.api.error.ApiErrorCode;

public class CouponAlreadyExistsException extends BusinessException {

    public CouponAlreadyExistsException(final String code) {
        super(ApiErrorCode.COUPON_ALREADY_EXISTS, "Coupon " + code + "already exists");
    }
}
