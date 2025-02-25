package com.ganzi.soccerhub.auth.application.port.in;

import com.ganzi.soccerhub.auth.application.result.AuthenticationTokens;

public interface RenewAccessTokenUseCase {
    AuthenticationTokens execute(String refreshToken);
}
