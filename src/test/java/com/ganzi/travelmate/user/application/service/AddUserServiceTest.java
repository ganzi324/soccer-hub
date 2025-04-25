package com.ganzi.travelmate.user.application.service;

import com.ganzi.travelmate.common.UserTestData;
import com.ganzi.travelmate.user.application.command.AddUserCommand;
import com.ganzi.travelmate.user.application.port.in.AddUserUseCase;
import com.ganzi.travelmate.user.application.port.out.AddUserPort;
import com.ganzi.travelmate.user.application.port.out.LoadUserPort;
import com.ganzi.travelmate.user.domain.User;
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
    void addNormalUserSuccess() {
        User user = UserTestData.defaultUser();

        givenLoadUserWillSucceed(user);
        givenSaveUserWillSucceed(user);
        givenEncodeWillSuccess();

        AddUserCommand command = AddUserCommand.createNormalUser(user.getName(), user.getEmail(), user.getPassword());
        assertThat(addUserUseCase.addUser(command)).isEqualTo(user);
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
