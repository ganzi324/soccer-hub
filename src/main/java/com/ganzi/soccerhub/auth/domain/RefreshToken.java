package com.ganzi.soccerhub.auth.domain;

import com.ganzi.soccerhub.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {

    private final RefreshTokenId id;
    private final User user;
    private final String token;
    private final Instant expiresAt;

    public static RefreshToken withoutId(User user, String token, Instant expiresAt) {
        return new RefreshToken(null, user, token, expiresAt);
    }

    public static RefreshToken withId(RefreshTokenId id, User user, String token, Instant expiresAt) {
        return new RefreshToken(id, user, token, expiresAt);
    }

    boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public RefreshToken renew(String token, Instant expiresAt) {
        return new RefreshToken(id, user, token, expiresAt);
    }

    public record RefreshTokenId(Long value) {}

    public Optional<RefreshTokenId> getId() {
        return Optional.ofNullable(this.id);
    }
}
