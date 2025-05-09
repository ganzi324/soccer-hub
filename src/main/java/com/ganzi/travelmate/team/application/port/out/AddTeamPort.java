package com.ganzi.travelmate.team.application.port.out;

import com.ganzi.travelmate.team.domain.Team;

public interface AddTeamPort {
    Team save(Team team);
}
