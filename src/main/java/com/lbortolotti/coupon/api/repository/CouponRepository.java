package com.lbortolotti.coupon.api.repository;

import com.lbortolotti.coupon.api.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    boolean existesByCode(String code);

}
