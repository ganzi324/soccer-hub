package com.ganzi.soccerhub.auth.application.port.in;

import com.ganzi.soccerhub.auth.application.command.LoginCommand;
import com.ganzi.soccerhub.auth.application.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse execute(LoginCommand command);
}
