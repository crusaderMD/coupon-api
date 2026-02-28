package com.lbortolotti.coupon.api.exception;

import com.lbortolotti.coupon.api.controller.dto.ErrorResponseDTO;
import com.lbortolotti.coupon.api.error.ApiErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex, HttpServletRequest request) {
        ErrorResponseDTO response = ErrorResponseDTO.of(HttpStatus.INTERNAL_SERVER_ERROR,
                ApiErrorCode.INTERNAL_SERVER_ERROR.getDefaultMessage(),
                ApiErrorCode.INTERNAL_SERVER_ERROR,
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(CouponAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCouponAlreadyExists(CouponAlreadyExistsException ex, HttpServletRequest request) {
        ErrorResponseDTO response = ErrorResponseDTO.of(HttpStatus.CONFLICT,
                ex.getMessage(),
                ApiErrorCode.COUPON_ALREADY_EXISTS,
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
