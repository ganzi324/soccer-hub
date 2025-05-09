package com.ganzi.travelmate.auth.application.port.out;

import com.ganzi.travelmate.auth.domain.RefreshToken;
import com.ganzi.travelmate.user.domain.User;

import java.util.Optional;

public interface LoadRefreshTokenPort {
    Optional<RefreshToken> loadByUserIdAndToken(User.UserId userId, String token);
}
