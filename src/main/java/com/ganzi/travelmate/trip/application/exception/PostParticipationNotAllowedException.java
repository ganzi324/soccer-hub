package com.ganzi.travelmate.trip.application.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class PostParticipationNotAllowedException extends CustomException {
    public PostParticipationNotAllowedException(String message) {
        super(ErrorCode.TRAVEL_MATE_JOIN_FAILED, message);
    }
}
