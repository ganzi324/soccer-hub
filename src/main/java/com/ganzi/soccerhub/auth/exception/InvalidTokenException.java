package com.ganzi.soccerhub.auth.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class InvalidTokenException extends CustomException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
