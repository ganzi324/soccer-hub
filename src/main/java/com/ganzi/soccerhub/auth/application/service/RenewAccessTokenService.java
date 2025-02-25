package com.ganzi.soccerhub.auth.application.service;

import com.ganzi.soccerhub.auth.JwtAuthProvider;
import com.ganzi.soccerhub.auth.SessionUser;
import com.ganzi.soccerhub.auth.application.port.in.GetRefreshTokenQuery;
import com.ganzi.soccerhub.auth.application.port.in.RenewAccessTokenUseCase;
import com.ganzi.soccerhub.auth.application.port.out.AddRefreshTokenPort;
import com.ganzi.soccerhub.auth.application.result.AuthenticationTokens;
import com.ganzi.soccerhub.auth.domain.RefreshToken;
import com.ganzi.soccerhub.auth.exception.InvalidTokenException;
import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.user.application.command.GetRefreshTokenCommand;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.domain.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class RenewAccessTokenService implements RenewAccessTokenUseCase {

    private final JwtAuthProvider jwtAuthProvider;
    private final GetUserQuery getUserQuery;
    private final GetRefreshTokenQuery getRefreshTokenQuery;
    private final AddRefreshTokenPort addRefreshTokenPort;

    @Override
    public AuthenticationTokens execute(@NonNull String refreshToken) {
        // 토큰 유효성 확인
        SessionUser sessionUser = jwtAuthProvider.getSessionUser(refreshToken);

        User user = getUserQuery.getUserById(User.UserId.of(sessionUser.id())).orElseThrow(InvalidTokenException::new);
        // TODO : 사용자 상태 확인
        RefreshToken currentRefreshToken = getRefreshToken(user, refreshToken).orElseThrow(InvalidTokenException::new);

        // 새 토큰 발급
        String newAccessToken = generateAccessToken(user);

        Instant refreshTokenExpiration = jwtAuthProvider.getRefreshTokenExpiresAt();
        String newRefreshToken = generateRefreshToken(user, refreshTokenExpiration);

        // DB 저장
        addRefreshTokenPort.save(currentRefreshToken.renew(newRefreshToken, refreshTokenExpiration));

        return new AuthenticationTokens(newAccessToken, newRefreshToken);
    }

    private Optional<RefreshToken> getRefreshToken(User user, String refreshToken) {
        GetRefreshTokenCommand command = new GetRefreshTokenCommand(user.getId().get(), refreshToken);
        return getRefreshTokenQuery.getRefreshToken(command);
    }

    private String generateAccessToken(User user) {
        Map<String, Object> claims = jwtAuthProvider.getClaimsByUser(user);
        return jwtAuthProvider.generateAccessToken(claims);
    }

    private String generateRefreshToken(User user, Instant expiration) {
        Map<String, Object> claims = jwtAuthProvider.getClaimsByUser(user);
        return jwtAuthProvider.generateRefreshToken(claims, expiration);
    }
}
