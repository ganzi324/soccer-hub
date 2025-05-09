package com.ganzi.travelmate.auth.application.command;

import com.ganzi.travelmate.common.SelfValidating;
import com.ganzi.travelmate.user.domain.User.UserId;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.Instant;

@Getter
public class AddRefreshTokenCommand extends SelfValidating<AddRefreshTokenCommand> {

    @NotNull
    private final UserId userId;

    @NotNull
    private final String token;

    @NotNull
    private final Instant expiresAt;

    public AddRefreshTokenCommand(UserId userId, String token, Instant expiresAt) {
        this.userId = userId;
        this.token = token;
        this.expiresAt = expiresAt;

        this.validateSelf();
    }
}
