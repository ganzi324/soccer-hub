package com.ganzi.travelmate.post.domain;

import com.ganzi.travelmate.team.domain.Member;
import com.ganzi.travelmate.team.domain.Team;
import lombok.NonNull;

public class GeneralPost extends Post {

    public GeneralPost(
            @NonNull Team.TeamId teamId,
            @NonNull Member.MemberId memberId,
            @NonNull String title
    ) {
        super(null, PostType.GENERAL, teamId, memberId, title);
    }
}
