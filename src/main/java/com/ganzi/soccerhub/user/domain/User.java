package com.ganzi.soccerhub.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final UserId id;

    @Getter private final String name;

    @Getter private final String email;

    @Getter private final String picture;

    public static User withoutId(
            String name,
            String email,
            String picture
    ) {
        return new User(null, name, email, picture);
    }

    public static User withId(
            UserId id,
            String name,
            String email,
            String picture
    ) {
        return new User(id, name, email, picture);
    }

    public Optional<UserId> getId() {
        return Optional.ofNullable(this.id);
    }

    public record UserId(Long value) {

    }
}
