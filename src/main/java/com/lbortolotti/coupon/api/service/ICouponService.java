package com.lbortolotti.coupon.api.service;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponUpdateRequestDTO;

import java.util.List;

public interface ICouponService {

    CouponResponseDTO saveCoupon(CouponRequestDTO requestDTO);

    CouponResponseDTO updateCoupon(CouponUpdateRequestDTO couponUpdateRequestDTO, String code);

    CouponResponseDTO findCouponByCode(String code);

    List<CouponResponseDTO> findAllCoupons();

    void deleteCoupon(String code);
}
