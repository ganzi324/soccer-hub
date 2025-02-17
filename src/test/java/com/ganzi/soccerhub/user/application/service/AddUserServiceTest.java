package com.ganzi.soccerhub.user.application.service;

import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.port.out.AddUserPort;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public class AddUserServiceTest {

    private final AddUserPort addUserPort = Mockito.mock(AddUserPort.class);
    private final BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

    private final AddUserService addUserService = new AddUserService(
            addUserPort,
            passwordEncoder
    );

    @Test
    void addUserSuccess() {
        User user = UserTestData.defaultUser();

        givenSaveUserWillSucceed(user);
        givenEncodeWillSuccess();

        AddUserCommand command = new AddUserCommand(user.getName(), user.getEmail(), user.getPassword(), user.getPicture(), user.getUserType());
        assertThat(addUserService.addUser(command)).isEqualTo(user.getId().get().value());
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
