package com.ganzi.soccerhub.common.web;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponseError {
    private final String errorCode;
    private final String message;
    private final String details;
    private final LocalDateTime timestamp;

    private ApiResponseError(String errorCode, String message, String details) {
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static ApiResponseError of(CustomException exception) {
        return new ApiResponseError(exception.getCode(), exception.getMessage(), null);
    }
}
