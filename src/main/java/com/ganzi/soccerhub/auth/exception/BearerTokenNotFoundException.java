package com.ganzi.soccerhub.auth.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class BearerTokenNotFoundException extends CustomException {
    public BearerTokenNotFoundException() {
        super(ErrorCode.BEARER_TOKEN_NOT_FOUND);
    }
}
