package com.ganzi.travelmate.common.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class DomainIdNotFoundException extends CustomException {

    public DomainIdNotFoundException(Class<?> clazz) {
        super(ErrorCode.DEFAULT, clazz.getName());
    }
}
