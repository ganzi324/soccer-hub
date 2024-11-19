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

    @Getter private final UserRole userRole;

    @Getter private final UserType userType;

    public static User withoutId(
            String name,
            String email,
            String picture,
            UserType userType
    ) {
        return new User(null, name, email, picture, UserRole.USER, userType);
    }

    public static User withId(
            UserId id,
            String name,
            String email,
            String picture,
            UserRole userRole,
            UserType userType
    ) {
        return new User(id, name, email, picture, userRole, userType);
    }

    public Optional<UserId> getId() {
        return Optional.ofNullable(this.id);
    }

    public record UserId(Long value) {

    }
}
