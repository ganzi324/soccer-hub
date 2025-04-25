package com.ganzi.travelmate.team.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.common.exception.DomainIdNotFoundException;
import com.ganzi.travelmate.team.application.command.AddTeamCommand;
import com.ganzi.travelmate.team.application.exception.DuplicateTeamNameException;
import com.ganzi.travelmate.team.application.port.in.AddTeamUseCase;
import com.ganzi.travelmate.team.application.port.out.AddTeamPort;
import com.ganzi.travelmate.team.application.port.out.LoadTeamPort;
import com.ganzi.travelmate.team.domain.Team;
import com.ganzi.travelmate.user.application.exception.UserNotFoundException;
import com.ganzi.travelmate.user.application.port.out.LoadUserPort;
import com.ganzi.travelmate.user.domain.User;
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
        User user = loadUserPort.loadUserById(command.getCreatedBy()).orElseThrow(UserNotFoundException::new);

        if (loadTeamPort.loadTeamByName(command.getName()).isPresent()) {
            throw new DuplicateTeamNameException(MessageFormat.format("This team name already exists - {0}", command.getName()));
        }

        Team newTeam = Team.withoutId(command.getName(), command.isPrivate(), command.getDescription(), command.getCreatedBy());
        Team savedTeam = addTeamPort.save(newTeam);

        return savedTeam.getId().orElseThrow(() -> new DomainIdNotFoundException(Team.class));
    }
}
