package com.ganzi.travelmate.common.web.exception;

import com.ganzi.travelmate.common.exception.DomainIdNotFoundException;
import com.ganzi.travelmate.common.web.ApiResponseError;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.MessageFormat;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ DomainIdNotFoundException.class })
    ResponseEntity<ApiResponseError<?>> handleDomainIdNotFoundException(DomainIdNotFoundException exception) {
        log.error(MessageFormat.format("Domain ID creation failed. (Type : {0})", exception.getDetails()));

        ApiResponseError<?> response = ApiResponseError.of(exception);
        HttpStatus status = exception.getStatus();

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler({ CustomException.class })
    ResponseEntity<ApiResponseError<?>> handleException(CustomException exception) {
        log.error(exception.getDetails(), exception);
        ApiResponseError<?> response = ApiResponseError.of(exception);
        HttpStatus status = exception.getStatus();

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    ResponseEntity<ApiResponseError<?>> handleBadRequestException(Exception exception) {
        ApiResponseError<?> response = ApiResponseError.of(ErrorCode.INVALID_REQUEST_PARAMETER, exception.getMessage());
        log.error(response.getDetails().toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ AuthenticationException.class })
    ResponseEntity<ApiResponseError<?>> handleAuthenticationException(AuthenticationException exception) {
        ApiResponseError<?> response = ApiResponseError.of(ErrorCode.DEFAULT, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ RuntimeException.class })
    ResponseEntity<ApiResponseError<?>> handleUndefinedException(Exception exception) {
        ApiResponseError<?> response = ApiResponseError.of(ErrorCode.DEFAULT, exception.getMessage());
        log.error(response.getDetails().toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
