package com.ganzi.soccerhub.user.application.port.in;

import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.domain.User;

public interface AddUserUseCase {
    User addUser(AddUserCommand command);
}
