package com.lbortolotti.coupon.api.controller;

import com.lbortolotti.coupon.api.controller.dto.CouponRequestDTO;
import com.lbortolotti.coupon.api.controller.dto.CouponResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Coupons", description = "Operations related to coupons")
public interface ICouponController {

    @Operation(
            summary = "Create a new coupon",
            description = "Creates a coupon with code, discount and expiration date"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Coupon successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "Coupon already exists")
    })
    ResponseEntity<CouponResponseDTO> createCoupon(CouponRequestDTO couponRequestDTO);


}
