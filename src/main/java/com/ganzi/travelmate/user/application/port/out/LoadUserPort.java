package com.ganzi.travelmate.user.application.port.out;

import com.ganzi.travelmate.user.domain.User;

import java.util.Optional;

public interface LoadUserPort {
    Optional<User> loadUserByEmail(String email);

    Optional<User> loadUserById(User.UserId id);

    Optional<User> loadUserByUserKey(String userKey);
}
