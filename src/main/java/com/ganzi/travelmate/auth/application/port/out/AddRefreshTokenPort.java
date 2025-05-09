package com.ganzi.travelmate.auth.application.port.out;

import com.ganzi.travelmate.auth.domain.RefreshToken;

public interface AddRefreshTokenPort {
    RefreshToken save(RefreshToken refreshToken);
}
