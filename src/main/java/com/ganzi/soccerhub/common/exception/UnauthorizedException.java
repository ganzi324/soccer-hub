package com.ganzi.soccerhub.common.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
