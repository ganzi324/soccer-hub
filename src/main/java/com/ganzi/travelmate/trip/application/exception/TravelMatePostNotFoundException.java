package com.ganzi.travelmate.trip.application.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class TravelMatePostNotFoundException extends CustomException {

    public TravelMatePostNotFoundException() {
        super(ErrorCode.TRAVEL_MATE_POST_NOT_FOUND);
    }
}
