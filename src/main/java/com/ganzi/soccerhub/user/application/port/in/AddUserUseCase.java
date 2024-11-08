package com.ganzi.soccerhub.user.application.port.in;

import com.ganzi.soccerhub.user.application.command.AddUserCommand;

public interface AddUserUseCase {
    Long addUser(AddUserCommand command);
}
