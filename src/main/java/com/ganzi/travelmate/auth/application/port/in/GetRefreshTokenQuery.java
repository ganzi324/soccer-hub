package com.ganzi.travelmate.auth.application.port.in;

import com.ganzi.travelmate.auth.domain.RefreshToken;
import com.ganzi.travelmate.user.application.command.GetRefreshTokenCommand;

import java.util.Optional;

public interface GetRefreshTokenQuery {
    Optional<RefreshToken> getRefreshToken(GetRefreshTokenCommand command);
}
