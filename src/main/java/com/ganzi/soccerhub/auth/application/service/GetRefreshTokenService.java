package com.ganzi.soccerhub.auth.application.service;

import com.ganzi.soccerhub.auth.application.port.in.GetRefreshTokenQuery;
import com.ganzi.soccerhub.auth.application.port.out.LoadRefreshTokenPort;
import com.ganzi.soccerhub.auth.domain.RefreshToken;
import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.user.application.command.GetRefreshTokenCommand;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetRefreshTokenService implements GetRefreshTokenQuery {

    private final LoadRefreshTokenPort loadRefreshTokenPort;

    @Override
    public Optional<RefreshToken> getRefreshToken(GetRefreshTokenCommand command) {
        return loadRefreshTokenPort.loadByUserIdAndToken(command.userId(), command.refreshToken());
    }
}
