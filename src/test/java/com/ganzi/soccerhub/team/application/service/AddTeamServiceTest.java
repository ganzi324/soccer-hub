package com.ganzi.soccerhub.team.application.service;

import com.ganzi.soccerhub.common.TeamTestData;
import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.team.application.command.AddTeamCommand;
import com.ganzi.soccerhub.team.application.port.in.AddTeamUseCase;
import com.ganzi.soccerhub.team.application.port.out.AddTeamPort;
import com.ganzi.soccerhub.team.application.port.out.LoadTeamPort;
import com.ganzi.soccerhub.team.domain.Team;
import com.ganzi.soccerhub.user.application.port.out.LoadUserPort;
import com.ganzi.soccerhub.user.domain.User;
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
        givenTeamDoesNotExist(team);
        givenSaveTeamWillSucceed(team);

        // when
        AddTeamCommand command = new AddTeamCommand(team.getName(), team.getDescription(), team.isPrivate(), user.getId().get());
        assertThat(addTeamUseCase.addTeam(command)).isEqualTo(team.getId());

    }

    private void givenLoadUserWillSucceed(User user) {
        given(loadUserPort.loadUserById(user.getId().get().value()))
                .willReturn(Optional.of(user));
    }

    private void givenTeamDoesNotExist(Team team) {
        given(loadTeamPort.loadTeamByName(anyString()))
                .willReturn(Optional.of(team));
    }

    private void givenSaveTeamWillSucceed(Team team) {
        given(addTeamPort.save(any(Team.class)))
                .willReturn(team);
    }

}
