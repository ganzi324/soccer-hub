package com.ganzi.travelmate.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.travelmate.auth.application.command.AddRefreshTokenCommand;
import com.ganzi.travelmate.auth.application.port.in.AddRefreshTokenUseCase;
import com.ganzi.travelmate.auth.dto.PrincipalInfo;
import com.ganzi.travelmate.user.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtAuthProvider jwtAuthProvider;
    private final AddRefreshTokenUseCase addRefreshTokenUseCase;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalInfo principalInfo = (PrincipalInfo) authentication.getPrincipal();

        Map<String, Object> claims = createClaims(principalInfo.user());
        String accessToken = generateAccessToken(claims);

        Instant refreshTokenExpiration = jwtAuthProvider.getRefreshTokenExpiresAt();
        String refreshToken = generateRefreshToken(claims, refreshTokenExpiration);

        saveRefreshToken(principalInfo.user().getId().get(), refreshToken, refreshTokenExpiration);

        sendTokensInResponse(response, accessToken, refreshToken);
    }

    private Map<String, Object> createClaims(User user) {
        return jwtAuthProvider.getClaimsByUser(user);
    }

    private String generateAccessToken(Map<String, Object> claims) {
        return jwtAuthProvider.generateAccessToken(claims);
    }

    private String generateRefreshToken(Map<String, Object> claims, Instant refreshTokenExpiration) {
        return jwtAuthProvider.generateRefreshToken(claims, refreshTokenExpiration);
    }

    private void saveRefreshToken(User.UserId userId, String refreshToken, Instant expiredAt) {
        AddRefreshTokenCommand command = new AddRefreshTokenCommand(userId, refreshToken, expiredAt);
        addRefreshTokenUseCase.addRefreshToken(command);
    }

    private void sendTokensInResponse(HttpServletResponse response, String accessToken, String refreshToken) throws IOException {
        LoginResponse responseBody = new LoginResponse(accessToken, refreshToken);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
    }

}
