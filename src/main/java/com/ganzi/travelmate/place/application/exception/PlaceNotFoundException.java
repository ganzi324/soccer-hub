package com.ganzi.travelmate.place.application.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class PlaceNotFoundException  extends CustomException {

    public PlaceNotFoundException() {
        super(ErrorCode.PLACE_NOT_FOUND);
    }
}
