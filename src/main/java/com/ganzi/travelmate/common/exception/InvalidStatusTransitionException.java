package com.ganzi.travelmate.common.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

import java.text.MessageFormat;

public class InvalidStatusTransitionException extends CustomException {

    public InvalidStatusTransitionException(Enum<?> currentStatus, Enum<?> attemptedStatus) {
        super(ErrorCode.INVALID_STATUS_TRANSITION, MessageFormat.format("Cannot change from current state {0} to state {1}.", currentStatus.toString(), attemptedStatus.toString()));
    }
}
