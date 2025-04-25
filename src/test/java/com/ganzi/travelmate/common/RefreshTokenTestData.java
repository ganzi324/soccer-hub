package com.ganzi.travelmate.common;

import com.ganzi.travelmate.auth.domain.RefreshToken;

import java.time.Instant;

public class RefreshTokenTestData {
    public static RefreshToken defaultRefreshToken(String token) {
        return RefreshToken.withId(new RefreshToken.RefreshTokenId(1L), UserTestData.defaultUser(), token, Instant.now().plusSeconds(10));
    }

    public static RefreshToken expiredRefreshToken(String token) {
        return RefreshToken.withId(new RefreshToken.RefreshTokenId(1L), UserTestData.defaultUser(), token, Instant.now().minusSeconds(10));
    }
}
