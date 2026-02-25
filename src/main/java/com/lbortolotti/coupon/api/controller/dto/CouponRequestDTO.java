package com.lbortolotti.coupon.api.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(name = "Coupon Request",
        description = "Payload used to create a new coupon with information provided by the client.")
public class CouponRequestDTO {

    @Schema(description = "Unique identifier of the coupon. Provided by the client. " +
            "Alphanumeric string, optionally containing hyphens",
            example = "DESC-50")
    @NotBlank(message = "Code is required")
    private String code;

    @Schema(description = "Discount percentage value between 1 and 50 applied in the coupon. Provided by the client.",
        example = "10")
    @NotNull(message = "Discount is required")
    @Min(value = 1, message = "Discount must be at least 1")
    @Max(value = 50, message = "Discount must not exceed 50")
    private Integer discount;

    @Schema(description = "Date when the coupon expires. Must be today or later. Provided by the client",
        example = "2026-02-25")
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
