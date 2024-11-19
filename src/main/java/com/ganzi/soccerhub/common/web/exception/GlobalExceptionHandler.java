package com.ganzi.soccerhub.common.web.exception;

import com.ganzi.soccerhub.common.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ CustomException.class })
    ResponseEntity<ApiResponse<?>> handleException(CustomException exception) {
        ApiResponse<?> response = ApiResponse.error(exception);
        HttpStatus status = exception.getStatus();

        return new ResponseEntity<>(response, status);
    }
}
