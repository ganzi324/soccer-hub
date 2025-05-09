package com.ganzi.travelmate.user.application.port.in;

import com.ganzi.travelmate.user.application.command.PatchUserCommand;
import com.ganzi.travelmate.user.domain.User;

public interface PatchUserUseCase {
    User patch(PatchUserCommand command, Long authenticatedUserId);
}
