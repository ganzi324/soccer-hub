package com.ganzi.travelmate.auth.application.service;

import com.ganzi.travelmate.auth.application.port.in.GetRefreshTokenQuery;
import com.ganzi.travelmate.auth.application.port.out.LoadRefreshTokenPort;
import com.ganzi.travelmate.auth.domain.RefreshToken;
import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.user.application.command.GetRefreshTokenCommand;
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
