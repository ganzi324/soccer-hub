package com.ganzi.soccerhub.team.adaptor.in.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class AddTeamResponse {

    private String teamId;

    static AddTeamResponse of(Long value) {
        return new AddTeamResponse(value.toString());
    }
}
