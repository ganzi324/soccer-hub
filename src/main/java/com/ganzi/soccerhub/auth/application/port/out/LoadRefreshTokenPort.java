package com.ganzi.soccerhub.auth.application.port.out;

import com.ganzi.soccerhub.auth.domain.RefreshToken;
import com.ganzi.soccerhub.user.domain.User;

import java.util.Optional;

public interface LoadRefreshTokenPort {
    Optional<RefreshToken> loadByUserIdAndToken(User.UserId userId, String token);
}
