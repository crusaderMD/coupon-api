package com.lbortolotti.coupon.api.repository;

import com.lbortolotti.coupon.api.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    boolean existsByCode(String code);

    Optional<Coupon> findByCode(String code);

    void deleteCoupon(Coupon coupon);
}
