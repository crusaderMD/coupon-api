package com.lbortolotti.coupon.api.service;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponUpdateRequestDTO;
import com.lbortolotti.coupon.api.domain.Coupon;
import com.lbortolotti.coupon.api.exception.CouponAlreadyExistsException;
import com.lbortolotti.coupon.api.exception.CouponNotFoundException;
import com.lbortolotti.coupon.api.mapper.CouponMapper;
import com.lbortolotti.coupon.api.repository.CouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponService implements ICouponService{

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public CouponResponseDTO saveCoupon(CouponRequestDTO requestDTO) {

        if (couponRepository.existsByCode(requestDTO.getCode())) {
            throw new CouponAlreadyExistsException(requestDTO.getCode());
        }

        Coupon coupon = CouponMapper.toEntity(requestDTO);

        Coupon savedCoupon = couponRepository.save(coupon);

        return CouponMapper.toResponse(savedCoupon);
    }

    @Transactional
    @Override
    public CouponResponseDTO updateCoupon(CouponUpdateRequestDTO couponUpdateRequestDTO, String code) {

        Coupon coupon = couponRepository
                .findByCode(code)
                .orElseThrow(() -> new CouponNotFoundException(code));

        coupon.update(couponUpdateRequestDTO.getDiscount(), couponUpdateRequestDTO.getExpirationDate());

        return CouponMapper.toResponse(coupon);
    }

    @Override
    public CouponResponseDTO findCouponByCode(String code) {

        Coupon coupon = couponRepository
                .findByCode(code)
                .orElseThrow(() -> new CouponNotFoundException(code));

        return CouponMapper.toResponse(coupon);
    }
}
