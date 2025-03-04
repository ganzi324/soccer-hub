package com.ganzi.soccerhub.team.domain;

import com.ganzi.soccerhub.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Team {

    private final TeamId id;

    private final String name;

    private boolean isPrivate;

    private String description;

    private User.UserId createdBy;

    public static Team withoutId(
            String name,
            boolean isPrivate,
            String description,
            User.UserId createdBy
    ) {
        return new Team(null, name, isPrivate, description, createdBy);
    }

    public static Team withId(
            TeamId id,
            String name,
            boolean isPrivate,
            String description,
            User.UserId createdBy
    ) {
        return new Team(id, name, isPrivate, description, createdBy);
    }

    public record TeamId(Long value) {

    }

    public Optional<TeamId> getId() {
        return Optional.ofNullable(this.id);
    }
}
