package com.ganzi.soccerhub.post.domain;

import com.ganzi.soccerhub.team.domain.Member;
import com.ganzi.soccerhub.team.domain.Team;
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
