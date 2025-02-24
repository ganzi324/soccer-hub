package com.ganzi.soccerhub.auth.application.port.in;

import com.ganzi.soccerhub.auth.application.command.AddRefreshTokenCommand;
import com.ganzi.soccerhub.auth.domain.RefreshToken;

public interface AddRefreshTokenUseCase {
    RefreshToken addRefreshToken(AddRefreshTokenCommand command);
}