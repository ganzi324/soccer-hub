package com.ganzi.soccerhub.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Member {

    @Getter private final MemberId id;

    @Getter private final String name;

    @Getter private final String password;

    @Getter private final String email;

    public record MemberId(Long value) {

    }
}
