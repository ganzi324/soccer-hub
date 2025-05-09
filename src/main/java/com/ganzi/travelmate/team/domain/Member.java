package com.ganzi.travelmate.team.domain;

import com.ganzi.travelmate.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private final MemberId id;

    private final User.UserId userId;

    private final Team.TeamId teamId;

    private final Role role;

    public static Member withoutId(
            User.UserId userId,
            Team.TeamId teamId,
            Role role
    ) {
        return new Member(null, userId, teamId, role);
    }

    public static Member withId(
            MemberId id,
            User.UserId userId,
            Team.TeamId teamId,
            Role role
    ) {
        return new Member(id, userId, teamId, role);
    }

    public record MemberId(Long value) {

    }
}
