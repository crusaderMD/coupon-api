package com.lbortolotti.coupon.api.controller.dto;

import java.time.LocalDate;

public class CouponResponseDTO {

    private Long id;

    private String code;

    private Integer discount;

    private LocalDate expirationDate;

    public CouponResponseDTO() {}

    public CouponResponseDTO(Long id, String code, Integer discount, LocalDate expirationDate) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        discount = discount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
