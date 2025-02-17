package com.ganzi.soccerhub.team.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Team {

    private final TeamId id;

    private final String name;

    private boolean isPrivate;

    private String description;

    public static Team withoutId(
            String name,
            boolean isPrivate,
            String description
    ) {
        return new Team(null, name, isPrivate, description);
    }

    public static Team withId(
            TeamId id,
            String name,
            boolean isPrivate,
            String description
    ) {
        return new Team(id, name, isPrivate, description);
    }

    public record TeamId(Long value) {

    }
}
