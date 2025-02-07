package com.ganzi.soccerhub.user.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.port.in.AddUserUseCase;
import com.ganzi.soccerhub.user.application.port.out.AddUserPort;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class AddUserService implements AddUserUseCase {

    private final AddUserPort addUserPort;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Long addUser(AddUserCommand command) {
        User user = User.withoutId(
                command.getName(),
                command.getEmail(),
                Optional.ofNullable(command.getPassword())
                        .map(passwordEncoder::encode)
                        .orElse(null),
                command.getPicture(),
                command.getUserType()
        );

        return addUserPort.save(user).getId().get().value();
    }
}
