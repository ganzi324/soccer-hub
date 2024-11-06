package com.ganzi.soccerhub.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User {

    @Getter private final UserId id;

    @Getter private final String name;

    @Getter private final String password;

    @Getter private final String email;

    public record UserId(Long value) {

    }
}
