package com.ganzi.soccerhub.team.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Team {

    private final TeamId id;

    private final String name;

    private boolean isPrivate;

    private String description;

    public record TeamId(Long value) {

    }
}
