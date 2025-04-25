package com.ganzi.travelmate.team.application.exception;

import com.ganzi.travelmate.common.web.exception.CustomException;
import com.ganzi.travelmate.common.web.exception.ErrorCode;

public class DuplicateTeamNameException extends CustomException {

    public DuplicateTeamNameException(String message) {
        super(ErrorCode.DUPLICATE_TEAM_NAME, message);
    }
}
