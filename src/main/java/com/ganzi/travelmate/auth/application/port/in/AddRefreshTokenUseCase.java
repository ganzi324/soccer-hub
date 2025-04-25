package com.ganzi.travelmate.auth.application.port.in;

import com.ganzi.travelmate.auth.application.command.AddRefreshTokenCommand;
import com.ganzi.travelmate.auth.domain.RefreshToken;

public interface AddRefreshTokenUseCase {
    RefreshToken addRefreshToken(AddRefreshTokenCommand command);
}