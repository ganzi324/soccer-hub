package com.ganzi.soccerhub.user.application.port.in;

import com.ganzi.soccerhub.user.application.command.PatchUserCommand;
import com.ganzi.soccerhub.user.domain.User;

public interface PatchUserUseCase {
    User patch(PatchUserCommand command, Long authenticatedUserId);
}
