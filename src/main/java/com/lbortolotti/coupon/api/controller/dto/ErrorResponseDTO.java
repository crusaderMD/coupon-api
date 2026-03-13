package com.lbortolotti.coupon.api.controller.dto;

import com.lbortolotti.coupon.api.error.ApiErrorCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private final LocalDateTime timeStamp;
    private final int status;
    private final String message;
    private final String path;
    private final String businessCode;


    private ErrorResponseDTO(LocalDateTime timeStamp, int status, String message, String path, String businessCode) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
        this.path = path;
        this.businessCode = businessCode;
    }

    public static ErrorResponseDTO of(HttpStatus status, String message, ApiErrorCode apiErrorCode, String path) {
        LocalDateTime timeStamp = LocalDateTime.now();
        return new ErrorResponseDTO(timeStamp, status.value(), message, path, apiErrorCode.name());
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getBusinessCode() {
        return businessCode;
    }
}
