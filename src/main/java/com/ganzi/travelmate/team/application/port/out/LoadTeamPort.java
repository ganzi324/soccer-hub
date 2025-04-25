package com.ganzi.travelmate.team.application.port.out;

import com.ganzi.travelmate.team.domain.Team;

import java.util.Optional;

public interface LoadTeamPort {

    Optional<Team> loadTeamByName(String name);
}
