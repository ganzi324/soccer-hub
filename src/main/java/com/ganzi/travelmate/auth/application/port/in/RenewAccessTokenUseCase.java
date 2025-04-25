package com.ganzi.travelmate.auth.application.port.in;

import com.ganzi.travelmate.auth.application.result.AuthenticationTokens;

public interface RenewAccessTokenUseCase {
    AuthenticationTokens execute(String refreshToken);
}
