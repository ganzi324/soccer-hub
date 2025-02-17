package com.ganzi.soccerhub.user.application.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class InvalidPasswordException extends CustomException {

    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
