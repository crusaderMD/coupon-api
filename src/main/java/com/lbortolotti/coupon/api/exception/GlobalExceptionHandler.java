package com.lbortolotti.coupon.api.exception;

import com.lbortolotti.coupon.api.controller.dto.ErrorResponseDTO;
import com.lbortolotti.coupon.api.error.ApiErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex,
                                                            HttpServletRequest request) {
        log.error("Unexpected error", ex);
        ErrorResponseDTO response = ErrorResponseDTO.of(HttpStatus.INTERNAL_SERVER_ERROR,
                ApiErrorCode.INTERNAL_SERVER_ERROR.getDefaultMessage(),
                ApiErrorCode.INTERNAL_SERVER_ERROR,
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException ex,
                                                                    HttpServletRequest request) {

        log.warn("Business error: {}", ex.getMessage());

        ApiErrorCode errorCode = ex.getApiErrorCode();

        ErrorResponseDTO response = ErrorResponseDTO.of(
                errorCode.getHttpStatus(),
                ex.getMessage(),
                errorCode,
                request.getRequestURI());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationError(MethodArgumentNotValidException ex,
                                                                  HttpServletRequest request) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ErrorResponseDTO response = ErrorResponseDTO.of(
                HttpStatus.BAD_REQUEST,
                errorMessage,
                ApiErrorCode.VALIDATION_ERROR,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidFormat(HttpMessageNotReadableException ex,
                                                                HttpServletRequest request) {
        String errorMessage = "Invalid expiration date format. Expected format yyyy-MM-dd";

        ErrorResponseDTO response = ErrorResponseDTO.of(
                HttpStatus.BAD_REQUEST,
                errorMessage,
                ApiErrorCode.VALIDATION_ERROR,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
