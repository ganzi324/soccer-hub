package com.ganzi.soccerhub.user.application.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
