package com.ganzi.soccerhub.trip.application.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class TravelMatePostNotFoundException extends CustomException {

    public TravelMatePostNotFoundException() {
        super(ErrorCode.TRAVEL_MATE_POST_NOT_FOUND);
    }
}
