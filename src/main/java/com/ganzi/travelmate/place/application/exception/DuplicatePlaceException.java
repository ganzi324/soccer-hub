package com.ganzi.travelmate.place.application.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class DuplicatePlaceException extends CustomException {

    public DuplicatePlaceException() {
        super(ErrorCode.DUPLICATE_PLACE);
    }
}
