package com.ganzi.travelmate.user.application.port.in;

import com.ganzi.travelmate.user.domain.User;

import java.util.Optional;

public interface GetUserQuery {
    Optional<User> getUserByEmail(String email);

    Optional<User> getUserById(User.UserId id);

    Optional<User> getUserByUserKey(String userKey);
}
