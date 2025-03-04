package com.ganzi.soccerhub.user.application.port.out;

import com.ganzi.soccerhub.user.domain.User;

import java.util.Optional;

public interface LoadUserPort {
    Optional<User> loadUserByEmail(String email);

    Optional<User> loadUserById(User.UserId id);
}
