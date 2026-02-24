package com.lbortolotti.coupon.api.service;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import com.lbortolotti.coupon.api.domain.Coupon;
import com.lbortolotti.coupon.api.mapper.CouponMapper;
import com.lbortolotti.coupon.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponService implements ICouponService{

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public CouponResponseDTO saveCoupon(CouponRequestDTO requestDTO) {

        if (couponRepository.existsByCode(requestDTO.getCode())) {
            throw new RuntimeException("Coupon already exists");
        }

        Coupon coupon = CouponMapper.toEntity(requestDTO);

        Coupon savedCoupon = couponRepository.save(coupon);

        return CouponMapper.toResponse(savedCoupon);
    }
}
