package com.ganzi.travelmate.user.application.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

import java.text.MessageFormat;

public class DuplicateUserIdException extends CustomException {

    public DuplicateUserIdException(String id) {
        super(ErrorCode.DUPLICATE_USER_ID, MessageFormat.format("input id : {0}", id));
    }

}
