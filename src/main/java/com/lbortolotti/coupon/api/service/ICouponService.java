package com.lbortolotti.coupon.api.service;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;

public interface ICouponService {

    public CouponResponseDTO saveCoupon(CouponRequestDTO requestDTO);
}
