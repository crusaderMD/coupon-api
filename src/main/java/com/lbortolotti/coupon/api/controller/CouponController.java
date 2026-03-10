package com.lbortolotti.coupon.api.controller;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponUpdateRequestDTO;
import com.lbortolotti.coupon.api.service.ICouponService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/coupons")
public class CouponController implements ICouponController{

private final ICouponService couponService;

    public CouponController(ICouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<CouponResponseDTO> createCoupon(
            @Valid @RequestBody CouponRequestDTO couponRequestDTO) {

        CouponResponseDTO couponResponse =  couponService.saveCoupon(couponRequestDTO);

        URI location = URI.create("/coupons/" + couponResponse.getCode());

        return ResponseEntity.created(location).body(couponResponse);
    }

    @PutMapping("/{code}")
    public ResponseEntity<CouponResponseDTO> updateCoupon(
            @Valid @RequestBody CouponUpdateRequestDTO updateRequestDTO,
            @PathVariable String code) {

        CouponResponseDTO couponResponse = couponService.updateCoupon(updateRequestDTO, code);

        return ResponseEntity.ok(couponResponse);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CouponResponseDTO> findCouponByCode(@PathVariable String code) {

        CouponResponseDTO couponResponse = couponService.findCouponByCode(code);

        return ResponseEntity.ok(couponResponse);
    }
}
