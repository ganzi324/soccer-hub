package com.ganzi.soccerhub.user.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.exception.DuplicateUserIdException;
import com.ganzi.soccerhub.user.application.port.in.AddUserUseCase;
import com.ganzi.soccerhub.user.application.port.out.AddUserPort;
import com.ganzi.soccerhub.user.application.port.out.LoadUserPort;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class AddUserService implements AddUserUseCase {

    private final AddUserPort addUserPort;
    private final LoadUserPort loadUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(AddUserCommand command) {
        User user = User.withoutId(
                command.getUserKey(),
                command.getName(),
                command.getEmail(),
                Optional.ofNullable(command.getPassword())
                        .map(passwordEncoder::encode)
                        .orElse(null),
                command.getPicture(),
                command.getUserType()
        );

        checkDuplication(user);

        return addUserPort.save(user);
    }

    private void checkDuplication(User user) {
        if (loadUserPort.loadUserByUserKey(user.getUserKey()).isPresent()) {
            throw new DuplicateUserIdException(user.getEmail());
        }
    }
}
