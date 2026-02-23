package com.lbortolotti.coupon.api.controller.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CouponRequestDTO {

    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "Discount is required")
    @Min(value = 1, message = "Discount must be at least 1")
    @Max(value = 50, message = "Discount must be at least 50")
    private Integer discount;

    @NotNull(message = "Expiration date is required")
    @FutureOrPresent(message = "Expiration date must be today or in the future")
    private LocalDate expirationDate;

    public CouponRequestDTO() {}

    public CouponRequestDTO(String code, Integer discount, LocalDate expirationDate) {
        this.code = code;
        this.discount = discount;
        this.expirationDate = expirationDate;
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
        this.discount = discount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
