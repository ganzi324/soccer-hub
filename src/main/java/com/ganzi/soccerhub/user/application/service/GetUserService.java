package com.ganzi.soccerhub.user.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.application.port.out.LoadUserPort;
import com.ganzi.soccerhub.user.domain.User;
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
    public Optional<User> getUserById(Long id) {
        return loadUserPort.loadUserById(id);
    }
}
