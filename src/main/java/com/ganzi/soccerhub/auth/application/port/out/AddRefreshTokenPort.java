package com.ganzi.soccerhub.auth.application.port.out;

import com.ganzi.soccerhub.auth.domain.RefreshToken;

public interface AddRefreshTokenPort {
    RefreshToken save(RefreshToken refreshToken);
}
