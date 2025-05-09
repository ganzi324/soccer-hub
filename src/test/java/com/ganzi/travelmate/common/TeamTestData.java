package com.ganzi.travelmate.common;

import com.ganzi.travelmate.team.domain.Team;

public class TeamTestData {

    public static Team defaultTeam() {
        return Team.withId(new Team.TeamId(1L), "BSP", false, "description", UserTestData.defaultUser().getId().get());
    }
}
