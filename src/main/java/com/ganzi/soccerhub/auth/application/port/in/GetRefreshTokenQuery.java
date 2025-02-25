package com.ganzi.soccerhub.auth.application.port.in;

import com.ganzi.soccerhub.auth.domain.RefreshToken;
import com.ganzi.soccerhub.user.application.command.GetRefreshTokenCommand;

import java.util.Optional;

public interface GetRefreshTokenQuery {
    Optional<RefreshToken> getRefreshToken(GetRefreshTokenCommand command);
}
