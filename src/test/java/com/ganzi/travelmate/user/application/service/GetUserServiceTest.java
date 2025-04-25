package com.ganzi.travelmate.user.application.service;

import com.ganzi.travelmate.common.UserTestData;
import com.ganzi.travelmate.user.application.port.in.GetUserQuery;
import com.ganzi.travelmate.user.application.port.out.LoadUserPort;
import com.ganzi.travelmate.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GetUserServiceTest {

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