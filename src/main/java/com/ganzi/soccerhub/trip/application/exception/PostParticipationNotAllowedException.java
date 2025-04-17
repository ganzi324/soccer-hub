package com.ganzi.soccerhub.trip.application.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class PostParticipationNotAllowedException extends CustomException {
    public PostParticipationNotAllowedException(String message) {
        super(ErrorCode.TRAVEL_MATE_JOIN_FAILED, message);
    }
}
