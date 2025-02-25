package com.ganzi.soccerhub.auth.application.service;

import com.ganzi.soccerhub.auth.application.command.AddRefreshTokenCommand;
import com.ganzi.soccerhub.auth.application.port.in.AddRefreshTokenUseCase;
import com.ganzi.soccerhub.auth.application.port.out.AddRefreshTokenPort;
import com.ganzi.soccerhub.auth.domain.RefreshToken;
import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.user.application.exception.UserNotFoundException;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddRefreshTokenService implements AddRefreshTokenUseCase {

    private final AddRefreshTokenPort addRefreshTokenPort;
    private final GetUserQuery getUserQuery;

    @Override
    public RefreshToken addRefreshToken(AddRefreshTokenCommand command) {
        User user = getUserQuery.getUserById(command.getUserId()).orElseThrow(UserNotFoundException::new);

        RefreshToken refreshToken = RefreshToken.withoutId(user, command.getToken(), command.getExpiresAt());
        addRefreshTokenPort.save(refreshToken);

        return refreshToken;
    }
}
