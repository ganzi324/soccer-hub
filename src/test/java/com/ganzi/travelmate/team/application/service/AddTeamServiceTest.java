package com.ganzi.travelmate.team.application.service;

import com.ganzi.travelmate.common.TeamTestData;
import com.ganzi.travelmate.common.UserTestData;
import com.ganzi.travelmate.team.application.command.AddTeamCommand;
import com.ganzi.travelmate.team.application.port.in.AddTeamUseCase;
import com.ganzi.travelmate.team.application.port.out.AddTeamPort;
import com.ganzi.travelmate.team.application.port.out.LoadTeamPort;
import com.ganzi.travelmate.team.domain.Team;
import com.ganzi.travelmate.user.application.port.out.LoadUserPort;
import com.ganzi.travelmate.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AddTeamServiceTest {

    private AddTeamUseCase addTeamUseCase;

    @Mock
    private LoadTeamPort loadTeamPort;

    @Mock
    private AddTeamPort addTeamPort;

    @Mock
    private LoadUserPort loadUserPort;

    @BeforeEach
    void setUp() {
        addTeamUseCase = new AddTeamService(loadTeamPort, addTeamPort, loadUserPort);
    }

    @Test
    void addTeamTest() {
        Team team = TeamTestData.defaultTeam();
        User user = UserTestData.defaultUser();

        // given
        givenLoadUserWillSucceed(user);
        givenTeamDoesNotExist();
        givenSaveTeamWillSucceed(team);

        // when
        AddTeamCommand command = new AddTeamCommand(team.getName(), team.isPrivate(), team.getDescription(), user.getId().get());
        assertThat(addTeamUseCase.addTeam(command)).isEqualTo(team.getId().get());

    }

    private void givenLoadUserWillSucceed(User user) {
        given(loadUserPort.loadUserById(user.getId().get()))
                .willReturn(Optional.of(user));
    }

    private void givenTeamDoesNotExist() {
        given(loadTeamPort.loadTeamByName(anyString()))
                .willReturn(Optional.empty());
    }

    private void givenSaveTeamWillSucceed(Team team) {
        given(addTeamPort.save(any(Team.class)))
                .willReturn(team);
    }

}
