package com.lbortolotti.coupon.api.exception;

import com.lbortolotti.coupon.api.error.ApiErrorCode;

public abstract class BusinessException extends RuntimeException{

    private final ApiErrorCode apiErrorCode;

    protected BusinessException(ApiErrorCode apiErrorCode) {
        super(apiErrorCode.getDefaultMessage());
        this.apiErrorCode = apiErrorCode;
    }

    protected BusinessException(ApiErrorCode apiErrorCode, String customMessage) {
        super(customMessage);
        this.apiErrorCode = apiErrorCode;
    }

    public ApiErrorCode getApiErrorCode() {
        return apiErrorCode;
    }
}
