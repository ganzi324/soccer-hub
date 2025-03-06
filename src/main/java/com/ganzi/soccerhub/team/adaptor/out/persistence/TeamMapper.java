package com.ganzi.soccerhub.team.adaptor.out.persistence;

import com.ganzi.soccerhub.team.domain.Team;
import com.ganzi.soccerhub.user.domain.User;
import org.springframework.stereotype.Component;

@Component
class TeamMapper {

    Team mapToDomainEntity(TeamJpaEntity team) {
        return Team.withId(
                new Team.TeamId(team.getId()),
                team.getName(),
                team.isPrivate(),
                team.getDescription(),
                new User.UserId(team.getCreatedBy())
        );
    }

    TeamJpaEntity mapToJpaEntity(Team team) {
        return new TeamJpaEntity(
                team.getId().map(Team.TeamId::value).orElse(null),
                team.getName(),
                team.isPrivate(),
                team.getDescription(),
                team.getCreatedBy().value()
        );
    }

}
