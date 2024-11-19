package com.ganzi.soccerhub.common.web.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public static CustomException getDefaultException() {
        return new CustomException();
    }

    public static CustomException of(ErrorCode code) {
        return new CustomException(code);
    }

    private CustomException() {
        super(ErrorCode.DEFAULT.getMessage());
        errorCode = ErrorCode.DEFAULT;
    }

    private CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return errorCode.getMessage();
    }

    public HttpStatus getStatus() {
        return errorCode.getStatus();
    }
}
