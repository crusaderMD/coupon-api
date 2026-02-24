package com.lbortolotti.coupon.api.controller;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import com.lbortolotti.coupon.api.service.ICouponService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/coupons")
public class CouponController {

private final ICouponService couponService;

    public CouponController(ICouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<CouponResponseDTO> createCoupon(
            @Valid @RequestBody CouponRequestDTO couponRequestDTO) {

        CouponResponseDTO couponResponse =  couponService.saveCoupon(couponRequestDTO);

        URI location = URI.create("/coupons/" + couponResponse.getId());

        return ResponseEntity.created(location).body(couponResponse);
    }
}
