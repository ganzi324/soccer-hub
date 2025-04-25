package com.ganzi.travelmate.user.application.port.in;

import com.ganzi.travelmate.user.application.command.AddUserCommand;
import com.ganzi.travelmate.user.domain.User;

public interface AddUserUseCase {
    User addUser(AddUserCommand command);
}
