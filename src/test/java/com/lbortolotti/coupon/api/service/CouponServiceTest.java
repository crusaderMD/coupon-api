package com.lbortolotti.coupon.api.service;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponUpdateRequestDTO;
import com.lbortolotti.coupon.api.domain.Coupon;
import com.lbortolotti.coupon.api.exception.CouponAlreadyExistsException;
import com.lbortolotti.coupon.api.exception.CouponNotFoundException;
import com.lbortolotti.coupon.api.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponService couponService;

    @Test
    void shouldSaveCouponWhenCodeNotExist(){

        String code = "TEST10";

        CouponRequestDTO request = createRequest();

        Coupon coupon = new Coupon(code, 20, request.getExpirationDate());

        when(couponRepository.existsByCode(code))
                .thenReturn(false);

        when(couponRepository.save(any(Coupon.class)))
                .thenReturn(coupon);

        CouponResponseDTO response = couponService.saveCoupon(request);

        assertNotNull(response);
        assertEquals(code, response.getCode());
        assertEquals(20, response.getDiscount());
        assertEquals(request.getExpirationDate(), response.getExpirationDate());

        verify(couponRepository).existsByCode(code);
        verify(couponRepository).save(any(Coupon.class));
    }

    @Test
    void shouldThrowExceptionWhenCodeExist() {

        String code = "TEST10";

        CouponRequestDTO request = createRequest();

        when(couponRepository.existsByCode(code))
                .thenReturn(true);

        assertThrows(CouponAlreadyExistsException.class, () -> {
            couponService.saveCoupon(request);
        });

        verify(couponRepository).existsByCode(code);
        verify(couponRepository, never()).save(any());
    }

    @Test
    void shouldUpdateCouponWhenExist() {

        String code = "TEST10";

        CouponUpdateRequestDTO updateRequest = createUpdateRequest();

        Coupon coupon = new Coupon(code, 20, updateRequest.getExpirationDate());

        when(couponRepository.findByCode(code))
                .thenReturn(Optional.of(coupon));

        CouponResponseDTO response = couponService.updateCoupon(updateRequest, code);

        assertNotNull(response);
        assertEquals(code, response.getCode());
        assertEquals(updateRequest.getDiscount(), coupon.getDiscount());

        verify(couponRepository).findByCode(code);
        verifyNoMoreInteractions(couponRepository);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingCoupon() {

        String code = "TEST10";

        CouponUpdateRequestDTO updateRequestDTO = createUpdateRequest();

        when(couponRepository.findByCode(code))
                .thenReturn(Optional.empty());

        assertThrows(CouponNotFoundException.class, () -> {
            couponService.updateCoupon(updateRequestDTO, code);
        });

        verify(couponRepository).findByCode(code);
        verifyNoMoreInteractions(couponRepository);

    }

    @Test
    void shouldReturnCouponWhenCouponExists() {

        String code = "TEST10";

        Coupon coupon = new Coupon("TEST10", 20, LocalDate.now());

        when(couponRepository.findByCode(code))
                .thenReturn(Optional.of(coupon));

        CouponResponseDTO response = couponService.findCouponByCode(code);

        assertNotNull(response);
        assertEquals(code, response.getCode());
        assertEquals(20, response.getDiscount());
        assertEquals(coupon.getExpirationDate(), response.getExpirationDate());

        verify(couponRepository).findByCode(code);
        verifyNoMoreInteractions(couponRepository);
    }

    @Test
    void shouldThrowExceptionWhenCouponDoesNotExist() {

        String code = "TEST10";

        when(couponRepository.findByCode(code))
                .thenReturn(Optional.empty());

        assertThrows(CouponNotFoundException.class, () -> {
            couponService.findCouponByCode(code);
        });

        verify(couponRepository).findByCode(code);
        verifyNoMoreInteractions(couponRepository);
    }

    @Test
    void shouldReturnAllExistingCoupons() {

        when(couponRepository.findAll())
                .thenReturn(createCouponList());

        List<CouponResponseDTO> responseList = couponService.findAllCoupons();

        assertNotNull(responseList);
        assertEquals(3, responseList.size());

        assertEquals("TEST10", responseList.get(0).getCode());
        assertEquals(20, responseList.get(0).getDiscount());

        assertEquals("TEST11", responseList.get(1).getCode());
        assertEquals(21, responseList.get(1).getDiscount());

        assertEquals("TEST12", responseList.get(2).getCode());
        assertEquals(22, responseList.get(2).getDiscount());

        verify(couponRepository).findAll();
        verifyNoMoreInteractions(couponRepository);
    }

    // TODO - findAll() exception test

    @Test
    void shouldDeleteCouponWhenExist() {

        String code = "TEST10";

        Coupon coupon = new Coupon("TEST10", 20, LocalDate.now());

        when(couponRepository.findByCode(code))
                .thenReturn(Optional.of(coupon));

        couponService.deleteCoupon(code);

        verify(couponRepository).findByCode(code);
        verify(couponRepository).deleteCoupon(coupon);
        verifyNoMoreInteractions(couponRepository);
    }

    @Test
    void shouldThrowExceptionWhenCouponToDeleteDoesNotExist() {

        String code = "TEST10";

        when(couponRepository.findByCode(code))
                .thenReturn(Optional.empty());

        assertThrows(CouponNotFoundException.class, () -> {
            couponService.deleteCoupon(code);
        });

        verify(couponRepository).findByCode(code);
        verifyNoMoreInteractions(couponRepository);
        verifyNoMoreInteractions(couponRepository);
    }

    private CouponRequestDTO createRequest() {
        CouponRequestDTO dto = new CouponRequestDTO();
        dto.setCode("TEST10");
        dto.setDiscount(20);
        dto.setExpirationDate(LocalDate.now().plusDays(10));
        return dto;
    }

    private CouponUpdateRequestDTO createUpdateRequest() {
        CouponUpdateRequestDTO dto = new CouponUpdateRequestDTO();
        dto.setDiscount(15);
        dto.setExpirationDate(LocalDate.now().plusDays(15));
        return dto;
    }

    private List<Coupon> createCouponList() {
        return List.of(
                new Coupon("TEST10", 20, LocalDate.now()),
                new Coupon("TEST11", 21, LocalDate.now().plusDays(5)),
                new Coupon("TEST12", 22, LocalDate.now(). plusDays(10))
        );
    }
}
