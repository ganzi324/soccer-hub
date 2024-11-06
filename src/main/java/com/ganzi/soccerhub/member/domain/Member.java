package com.ganzi.soccerhub.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class Member {

    @Getter private final MemberId id;

    @Getter private final String name;

    @Getter private final String password;

    @Getter private final String email;

    private final List<MemberRole> roles;

    public MemberRole getRole(Long teamId) {
        return this.roles.stream()
                .filter(memberRole ->
                    memberRole.teamId().equals(teamId)
                )
                .findFirst()
                .orElse(null);
    }

    public record MemberId(Long value) {

    }
}
