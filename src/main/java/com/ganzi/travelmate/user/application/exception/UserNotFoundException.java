package com.ganzi.travelmate.user.application.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
