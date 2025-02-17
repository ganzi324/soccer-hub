package com.ganzi.soccerhub.user.application.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

import java.text.MessageFormat;

public class DuplicateUserIdException extends CustomException {

    public DuplicateUserIdException(String id) {
        super(ErrorCode.DUPLICATE_USER_ID, MessageFormat.format("input id : {0}", id));
    }

}
