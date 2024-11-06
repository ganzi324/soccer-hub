package com.ganzi.soccerhub.team.domain;

import com.ganzi.soccerhub.member.domain.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamMember {

    private final TeamMemberId id;

    private final Member.MemberId memberId;

    private final Team.TeamId teamId;

    private final Role role;

    public record TeamMemberId(Long value) {

    }
}
