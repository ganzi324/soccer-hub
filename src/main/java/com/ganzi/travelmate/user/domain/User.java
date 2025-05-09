package com.ganzi.travelmate.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final UserId id;

    @Getter private final String userKey;

    @Getter private final String name;

    @Getter private final String email;

    @Getter private final String password;

    @Getter private final String picture;

    @Getter private final UserRole userRole;

    @Getter private final UserType userType;

    public static User withoutId(
            String userKey,
            String name,
            String email,
            String password,
            String picture,
            UserType userType
    ) {
        return new User(null, userKey, name, email, password, picture, UserRole.USER, userType);
    }

    public static User withId(
            UserId id,
            String userKey,
            String name,
            String email,
            String password,
            String picture,
            UserRole userRole,
            UserType userType
    ) {
        return new User(id, userKey, name, email, password, picture, userRole, userType);
    }

    public Optional<UserId> getId() {
        return Optional.ofNullable(this.id);
    }

    public User update(UserUpdateProfile updateVo) {
        String name = Optional.ofNullable(updateVo.name()).orElse(this.name);
        String picture = Optional.ofNullable(updateVo.name()).orElse(this.picture);

        return new User(this.id, this.userKey, name, this.email, this.password, picture, this.userRole, this.userType);
    }

    public record UserId(Long value) {
        public static UserId of(Long id) {
            return new UserId(id);
        }
    }
}
