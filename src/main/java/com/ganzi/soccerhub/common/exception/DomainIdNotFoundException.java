package com.ganzi.soccerhub.common.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class DomainIdNotFoundException extends CustomException {

    public DomainIdNotFoundException(Class<?> clazz) {
        super(ErrorCode.DEFAULT, clazz.getName());
    }
}
