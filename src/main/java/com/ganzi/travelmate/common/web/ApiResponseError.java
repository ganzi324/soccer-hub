package com.ganzi.travelmate.common.web;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponseError<T> {
    private final String errorCode;
    private final String message;
    private final T details;
    private final LocalDateTime timestamp;

    private ApiResponseError(String errorCode, String message, T details) {
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public static ApiResponseError<?> of(CustomException exception) {
        return new ApiResponseError<>(exception.getCode(), exception.getMessage(), exception.getDetails());
    }

    public static <T> ApiResponseError<T> of(ErrorCode errorCode, T details) {
        return new ApiResponseError<>(errorCode.getCode(), errorCode.getMessage(), details);
    }
}
