package com.lbortolotti.coupon.api.service;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponUpdateRequestDTO;

public interface ICouponService {

    CouponResponseDTO saveCoupon(CouponRequestDTO requestDTO);

    CouponResponseDTO updateCoupon(CouponUpdateRequestDTO couponUpdateRequestDTO, String code);

    CouponResponseDTO findCouponByCode(String code);
}
