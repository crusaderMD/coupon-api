package com.lbortolotti.coupon.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

@Schema(name = "Coupon Request",
        description = "Payload used to update an existing coupon with information provided by the client.")
public class CouponUpdateRequestDTO {

    @Schema(description = "Discount percentage value between 1 and 50 applied in the coupon. Provided by the client.",
            example = "10")
    @Min(value = 1, message = "Discount must be at least 1")
    @Max(value = 50, message = "Discount must not exceed 50")
    private Integer discount;

    @Schema(description = "Date when the coupon expires. Must be today or later. Provided by the client",
            example = "2026-02-25")
    @FutureOrPresent(message = "Expiration date must be today or in the future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    public CouponUpdateRequestDTO() {}

    public CouponUpdateRequestDTO(Integer discount, LocalDate expirationDate) {
        this.discount = discount;
        this.expirationDate = expirationDate;
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
