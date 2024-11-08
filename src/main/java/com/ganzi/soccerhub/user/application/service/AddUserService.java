package com.ganzi.soccerhub.user.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.port.in.AddUserUseCase;
import com.ganzi.soccerhub.user.application.port.out.AddUserPort;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddUserService implements AddUserUseCase {

    private final AddUserPort addUserPort;

    @Override
    public Long addUser(AddUserCommand command) {
        User user = User.withoutId(
                command.getName(),
                command.getPassword(),
                command.getEmail()
        );

        return addUserPort.save(user).getId().get().value();
    }
}
