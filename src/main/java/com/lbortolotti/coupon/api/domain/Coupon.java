package com.lbortolotti.coupon.api.domain;

import com.lbortolotti.coupon.api.constantes.ApiConstants;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Integer discount;

    @Column(nullable = false)
    private LocalDate expirationDate;

    protected Coupon() {}

    public Coupon(String code, Integer discount, LocalDate expirationDate) {

        validateDiscountAndExpirationDate(discount, expirationDate);

        this.code = code;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public Integer getDiscount() {
        return discount;
    }

    private void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    private void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void update(Integer discount, LocalDate expirationDate) {

        validateDiscountAndExpirationDate(discount, expirationDate);

        this.discount = discount;
        this.expirationDate = expirationDate;
    }

    private void validateDiscountAndExpirationDate(Integer discount, LocalDate expirationDate) {

        if (discount == null) {
            throw new RuntimeException("Discount cannot be null"); // change to specific exception
        }

        if (expirationDate == null) {
            throw new RuntimeException("Expiration date cannot be null"); // change to specific exception
        }

        if (discount < ApiConstants.minCouponDiscount || discount > ApiConstants.maxCouponDiscount) {
            throw new RuntimeException(""); // change for specific InvalidDiscountException
        }

        final LocalDate validateExpirationDate = LocalDate.now();

        if (expirationDate.isBefore(validateExpirationDate)) {
            throw new RuntimeException(""); // change for specific InvalidExpirationDateException
        }
    }
}
