package com.lbortolotti.coupon.api.controller;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ICouponController {

    ResponseEntity<CouponResponseDTO> createCoupon(CouponRequestDTO couponRequestDTO);


}
