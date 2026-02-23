package com.lbortolotti.coupon.api.service;

import com.lbortolotti.coupon.api.domain.Coupon;
import com.lbortolotti.coupon.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon saveCoupon(Coupon coupon) {

        if (couponRepository.existsByCode(coupon.getCode())) {
            throw new RuntimeException("Coupon already exists");
        }

        return couponRepository.save(coupon);
    }
}
