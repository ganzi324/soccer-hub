package com.ganzi.soccerhub.team.adaptor.out.persistence;

import com.ganzi.soccerhub.common.TeamTestData;
import com.ganzi.soccerhub.common.infra.persistence.JpaConfig;
import com.ganzi.soccerhub.team.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import(JpaConfig.class)
public class TeamPersistenceAdaptorTest {

    private TeamPersistenceAdaptor teamPersistenceAdaptor;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        TeamMapper teamMapper = new TeamMapper();
        teamPersistenceAdaptor = new TeamPersistenceAdaptor(teamRepository, teamMapper);
    }

    @Test
    void addTeam_ValidTeam_Succeed() {
        // given
        Team newTeam = TeamTestData.defaultTeam();

        Team savedTeam = teamPersistenceAdaptor.save(newTeam);

        // then
        assertThat(savedTeam.getId()).isEqualTo(newTeam.getId());
        assertThat(savedTeam.getName()).isEqualTo(newTeam.getName());
        assertThat(savedTeam.getDescription()).isEqualTo(newTeam.getDescription());
        assertThat(savedTeam.getCreatedBy()).isEqualTo(newTeam.getCreatedBy());
    }

}
