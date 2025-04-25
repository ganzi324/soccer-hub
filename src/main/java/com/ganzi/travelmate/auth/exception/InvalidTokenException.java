package com.ganzi.travelmate.auth.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class InvalidTokenException extends CustomException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
