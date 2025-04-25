package com.ganzi.travelmate.post.domain;

import com.ganzi.travelmate.team.domain.Member;
import com.ganzi.travelmate.team.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Post {

    private final PostId id;

    private final PostType type;

    private final Team.TeamId teamId;

    private final Member.MemberId memberId;

    private String title;

    public record PostId(Long value) {

    }
}
