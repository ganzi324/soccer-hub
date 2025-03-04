package com.ganzi.soccerhub.team.adaptor.out.persistence;

import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.team.application.port.out.AddTeamPort;
import com.ganzi.soccerhub.team.application.port.out.LoadTeamPort;
import com.ganzi.soccerhub.team.domain.Team;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeamPersistenceAdaptor implements LoadTeamPort, AddTeamPort {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public Optional<Team> loadTeamByName(String name) {
        return teamRepository.findByName(name).map(teamMapper::mapToDomainEntity);
    }

    @Override
    public Team save(Team team) {
        TeamJpaEntity teamJpaEntity = teamMapper.mapToJpaEntity(team);
        teamRepository.save(teamJpaEntity);

        return teamMapper.mapToDomainEntity(teamJpaEntity);
    }
}
