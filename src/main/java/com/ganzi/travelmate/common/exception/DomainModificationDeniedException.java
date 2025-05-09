package com.ganzi.travelmate.common.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

import java.text.MessageFormat;

public class DomainModificationDeniedException extends CustomException {

    public DomainModificationDeniedException(String target, String message) {
        super(ErrorCode.DOMAIN_MODIFICATION_DENIED, MessageFormat.format("target : {0}, reason : {1}", target, message));
    }
}
