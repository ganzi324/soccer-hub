package com.ganzi.soccerhub.place.application.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class DuplicatePlaceException extends CustomException {

    public DuplicatePlaceException() {
        super(ErrorCode.DUPLICATE_PLACE);
    }
}
