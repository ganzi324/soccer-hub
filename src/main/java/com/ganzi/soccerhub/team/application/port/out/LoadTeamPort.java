package com.ganzi.soccerhub.team.application.port.out;

import com.ganzi.soccerhub.team.domain.Team;

import java.util.Optional;

public interface LoadTeamPort {

    Optional<Team> loadTeamByName(String name);
}
