package com.ganzi.soccerhub.team.application.port.out;

import com.ganzi.soccerhub.team.domain.Team;

public interface AddTeamPort {
    Team save(Team team);
}
