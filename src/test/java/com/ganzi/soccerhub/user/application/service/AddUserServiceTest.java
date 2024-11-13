package com.ganzi.soccerhub.user.application.service;

import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.port.out.AddUserPort;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class AddUserServiceTest {

    private final AddUserPort addUserPort = Mockito.mock(AddUserPort.class);

    private final AddUserService addUserService = new AddUserService(addUserPort);

    @Test
    void addUserSuccess() {
        User user = UserTestData.defaultUser();

        givenSaveUserWillSucceed(user);

        AddUserCommand command = new AddUserCommand(user.getName(), user.getEmail(), user.getPicture());
        assertThat(addUserService.addUser(command)).isEqualTo(user.getId().get().value());
    }

    private void givenSaveUserWillSucceed(User user) {
        given(addUserPort.save(any(User.class)))
                .willReturn(user);
    }
}
