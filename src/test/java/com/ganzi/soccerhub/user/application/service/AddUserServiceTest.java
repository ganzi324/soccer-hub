package com.ganzi.soccerhub.user.application.service;

import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.port.in.AddUserUseCase;
import com.ganzi.soccerhub.user.application.port.out.AddUserPort;
import com.ganzi.soccerhub.user.application.port.out.LoadUserPort;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public class AddUserServiceTest {

    private final AddUserPort addUserPort = Mockito.mock(AddUserPort.class);
    private final LoadUserPort loadUserPort = Mockito.mock(LoadUserPort.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

    private final AddUserUseCase addUserUseCase = new AddUserService(
            addUserPort,
            loadUserPort,
            passwordEncoder
    );

    @Test
    void addUserSuccess() {
        User user = UserTestData.defaultUser();

        givenLoadUserWillSucceed(user);
        givenSaveUserWillSucceed(user);
        givenEncodeWillSuccess();

        AddUserCommand command = new AddUserCommand(user.getName(), user.getEmail(), user.getPassword(), user.getPicture(), user.getUserType());
        assertThat(addUserUseCase.addUser(command)).isEqualTo(user.getId().get().value());
    }

    private void givenLoadUserWillSucceed(User user) {
        given(loadUserPort.loadUserByEmail(user.getEmail()))
                .willReturn(Optional.empty());
    }

    private void givenSaveUserWillSucceed(User user) {
        given(addUserPort.save(any(User.class)))
                .willReturn(user);
    }

    private void givenEncodeWillSuccess() {
        given(passwordEncoder.encode(anyString()))
                .willReturn("encoded_password");
    }
}
