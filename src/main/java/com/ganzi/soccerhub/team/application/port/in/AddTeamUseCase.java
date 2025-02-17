package com.ganzi.soccerhub.team.application.port.in;

import com.ganzi.soccerhub.team.application.command.AddTeamCommand;
import com.ganzi.soccerhub.team.domain.Team;

public interface AddTeamUseCase {
    Team.TeamId addTeam(AddTeamCommand command);
}
