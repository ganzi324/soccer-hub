package com.ganzi.soccerhub.common;

import com.ganzi.soccerhub.auth.domain.RefreshToken;

import java.time.Instant;

public class RefreshTokenTestData {
    public static RefreshToken defaultRefreshToken(String token) {
        return RefreshToken.withId(new RefreshToken.RefreshTokenId(1L), UserTestData.defaultUser(), token, Instant.now().plusSeconds(10));
    }
}
