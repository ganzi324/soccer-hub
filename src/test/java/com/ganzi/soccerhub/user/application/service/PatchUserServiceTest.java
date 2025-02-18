package com.ganzi.soccerhub.user.application.service;

import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.application.port.out.LoadUserPort;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PatchUserServiceTest {

    private GetUserQuery getUserQuery;

    @Mock
    private LoadUserPort loadUserPort;

    @BeforeEach
    void setUp() {
        getUserQuery = new GetUserService(loadUserPort);
    }

    @Test
    void getUserTest() {
        User user = UserTestData.defaultUser();

        // given
        given(loadUserPort.loadUserByEmail(user.getEmail())).willReturn(Optional.of(user));

        // when
        Optional<User> loadedUser = getUserQuery.getUserByEmail(user.getEmail());

        // assert
        assertThat(loadedUser.get()).isEqualTo(user);
    }
}