package com.ganzi.travelmate.user.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.user.application.port.in.GetUserQuery;
import com.ganzi.travelmate.user.application.port.out.LoadUserPort;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
class GetUserService implements GetUserQuery {

    private final LoadUserPort loadUserPort;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return loadUserPort.loadUserByEmail(email);
    }

    @Override
    public Optional<User> getUserById(User.UserId id) {
        return loadUserPort.loadUserById(id);
    }

    @Override
    public Optional<User> getUserByUserKey(String userKey) {
        return loadUserPort.loadUserByUserKey(userKey);
    }
}
