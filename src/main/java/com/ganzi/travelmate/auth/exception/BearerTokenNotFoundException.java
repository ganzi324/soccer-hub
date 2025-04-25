package com.ganzi.travelmate.auth.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class BearerTokenNotFoundException extends CustomException {
    public BearerTokenNotFoundException() {
        super(ErrorCode.BEARER_TOKEN_NOT_FOUND);
    }
}
