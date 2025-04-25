package com.ganzi.travelmate.team.adaptor.out.persistence;

import com.ganzi.travelmate.common.PersistenceAdapter;
import com.ganzi.travelmate.team.application.port.out.AddTeamPort;
import com.ganzi.travelmate.team.application.port.out.LoadTeamPort;
import com.ganzi.travelmate.team.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeamPersistenceAdaptor implements LoadTeamPort, AddTeamPort {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    @Transactional(readOnly = true)
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
