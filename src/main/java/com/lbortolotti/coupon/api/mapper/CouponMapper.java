package com.lbortolotti.coupon.api.mapper;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import com.lbortolotti.coupon.api.domain.Coupon;

import java.util.Objects;

public class CouponMapper {

    private CouponMapper() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static Coupon toEntity(CouponRequestDTO requestDTO) {

        Objects.requireNonNull(requestDTO, "CouponRequestDTO cannot be null");

        return new Coupon(
                requestDTO.getCode(),
                requestDTO.getDiscount(),
                requestDTO.getExpirationDate()
        );
    }

    public static CouponResponseDTO toResponse(Coupon couponEntity) {

        Objects.requireNonNull(couponEntity, "Coupon cannot be null");

        return new CouponResponseDTO(
                couponEntity.getId(),
                couponEntity.getCode(),
                couponEntity.getDiscount(),
                couponEntity.getExpirationDate()
        );
    }
}
