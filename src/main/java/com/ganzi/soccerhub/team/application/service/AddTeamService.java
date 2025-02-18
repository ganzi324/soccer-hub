package com.ganzi.soccerhub.team.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.team.application.command.AddTeamCommand;
import com.ganzi.soccerhub.team.application.exception.DuplicateTeamNameException;
import com.ganzi.soccerhub.team.application.port.in.AddTeamUseCase;
import com.ganzi.soccerhub.team.application.port.out.AddTeamPort;
import com.ganzi.soccerhub.team.application.port.out.LoadTeamPort;
import com.ganzi.soccerhub.team.domain.Team;
import com.ganzi.soccerhub.user.application.exception.UserNotFoundException;
import com.ganzi.soccerhub.user.application.port.out.LoadUserPort;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;

@UseCase
@RequiredArgsConstructor
public class AddTeamService implements AddTeamUseCase {

    private final LoadTeamPort loadTeamPort;
    private final AddTeamPort addTeamPort;
    private final LoadUserPort loadUserPort;


    @Override
    public Team.TeamId addTeam(AddTeamCommand command) {
        // 생성자 조회 - 유효한 사용자 확인(팀 생성 제약)
        User user = loadUserPort.loadUserById(command.getCreatedBy().value()).orElseThrow(UserNotFoundException::new);

        loadTeamPort.loadTeamByName(command.getName()).orElseThrow(() -> new DuplicateTeamNameException(MessageFormat.format("This team name already exists - {0}", command.getName())));

        Team newTeam = Team.withoutId(command.getName(), command.isPrivate(), command.getDescription(), command.getCreatedBy());
        Team savedTeam = addTeamPort.save(newTeam);

        return savedTeam.getId();
    }
}
