package com.ganzi.soccerhub.auth.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import com.ganzi.soccerhub.user.domain.User.UserId;
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
