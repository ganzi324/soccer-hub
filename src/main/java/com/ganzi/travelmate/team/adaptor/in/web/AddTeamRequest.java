package com.ganzi.travelmate.team.adaptor.in.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class AddTeamRequest {

    private String name;

    private boolean isPrivate;

    private String description;
}
