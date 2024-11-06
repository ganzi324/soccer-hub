package com.ganzi.soccerhub.post.domain;

import com.ganzi.soccerhub.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public abstract class Post {

    private final PostId id;

    private final PostType type;

    private final LocalDateTime createAt;

    private final User.UserId userId;

    private String title;

    public record PostId(Long value) {

    }
}
