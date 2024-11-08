package com.ganzi.soccerhub.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final UserId id;

    @Getter private final String name;

    @Getter private final String password;

    @Getter private final String email;

    public static User withoutId(
            String name,
            String password,
            String email
    ) {
        return new User(null, name, password, email);
    }

    public static User withId(
            UserId id,
            String name,
            String password,
            String email
    ) {
        return new User(id, name, password, email);
    }

    public Optional<UserId> getId() {
        return Optional.ofNullable(this.id);
    }

    public record UserId(Long value) {

    }
}
