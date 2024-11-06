package com.ganzi.soccerhub.team.domain;

import com.ganzi.soccerhub.user.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Member {

    private final MemberId id;

    private final User.UserId userId;

    private final Team.TeamId teamId;

    private final Role role;

    public record MemberId(Long value) {

    }
}
