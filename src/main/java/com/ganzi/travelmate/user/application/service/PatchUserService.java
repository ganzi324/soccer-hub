package com.ganzi.travelmate.user.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.common.exception.UnauthorizedException;
import com.ganzi.travelmate.user.application.command.PatchUserCommand;
import com.ganzi.travelmate.user.application.exception.UserNotFoundException;
import com.ganzi.travelmate.user.application.port.in.PatchUserUseCase;
import com.ganzi.travelmate.user.application.port.out.PatchUserPort;
import com.ganzi.travelmate.user.domain.User;
import com.ganzi.travelmate.user.domain.User.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class PatchUserService implements PatchUserUseCase {

    private final PatchUserPort patchUserPort;
    private final GetUserService getUserService;

    @Override
    public User patch(PatchUserCommand command, Long authenticatedUserId) {
        if (!command.getId().equals(authenticatedUserId)) {
            log.warn(MessageFormat.format("User information can only be modified by the user. (Initiator : {0}, Target: {1}", authenticatedUserId, command.getId()));
            throw new UnauthorizedException();
        }

        User user = getUserService.getUserById(UserId.of(command.getId())).orElseThrow(UserNotFoundException::new);
        User updatedUser = user.update(command.toUpdateVo());
        patchUserPort.patch(updatedUser);

        return updatedUser;
    }
}
