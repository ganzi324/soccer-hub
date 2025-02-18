package com.ganzi.soccerhub.team.application.exception;

import com.ganzi.soccerhub.common.web.exception.CustomException;
import com.ganzi.soccerhub.common.web.exception.ErrorCode;

public class DuplicateTeamNameException extends CustomException {

    public DuplicateTeamNameException(String message) {
        super(ErrorCode.DUPLICATE_TEAM_NAME, message);
    }
}
