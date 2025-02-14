package com.ganzi.soccerhub.common.web.exception;

import com.ganzi.soccerhub.common.web.ApiResponseError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ CustomException.class })
    ResponseEntity<ApiResponseError<?>> handleException(CustomException exception) {
        ApiResponseError<?> response = ApiResponseError.of(exception);
        HttpStatus status = exception.getStatus();

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    ResponseEntity<ApiResponseError<?>> handleConstraintViolationException(ConstraintViolationException exception) {
        ApiResponseError<?> response = ApiResponseError.of(ErrorCode.INVALID_REQUEST_PARAMETER, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ AuthenticationException.class })
    ResponseEntity<ApiResponseError<?>> handleAuthenticationException(AuthenticationException exception) {
        ApiResponseError<?> response = ApiResponseError.of(ErrorCode.DEFAULT, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
