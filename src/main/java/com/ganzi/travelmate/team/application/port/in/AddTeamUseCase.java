package com.ganzi.travelmate.team.application.port.in;

import com.ganzi.travelmate.team.application.command.AddTeamCommand;
import com.ganzi.travelmate.team.domain.Team;

public interface AddTeamUseCase {
    Team.TeamId addTeam(AddTeamCommand command);
}
