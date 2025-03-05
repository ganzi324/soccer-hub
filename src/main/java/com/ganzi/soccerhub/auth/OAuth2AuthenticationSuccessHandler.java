package com.ganzi.soccerhub.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.soccerhub.auth.application.command.AddRefreshTokenCommand;
import com.ganzi.soccerhub.auth.application.port.in.AddRefreshTokenUseCase;
import com.ganzi.soccerhub.auth.dto.PrincipalInfo;
import com.ganzi.soccerhub.user.domain.User;
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

        Map<String, Object> claims = createClaims(principalInfo);
        String accessToken = generateAccessToken(claims);

        Instant refreshTokenExpiration = jwtAuthProvider.getRefreshTokenExpiresAt();
        String refreshToken = generateRefreshToken(claims, refreshTokenExpiration);

        saveRefreshToken(principalInfo.getAttribute(JwtAuthProvider.AUDIENCE), refreshToken, refreshTokenExpiration);

        sendTokensInResponse(response, accessToken, refreshToken);
    }

    private Map<String, Object> createClaims(PrincipalInfo principalInfo) {
        Map<String, Object> attributes = principalInfo.getAttributes();
        return Map.of(
                JwtAuthProvider.AUDIENCE, attributes.get(JwtAuthProvider.AUDIENCE),
                JwtAuthProvider.USERNAME, attributes.get("name"),
                JwtAuthProvider.AUTHORITY, JwtClaimConverter.extractRoleNames(principalInfo.getAuthorities())
        );
    }

    private String generateAccessToken(Map<String, Object> claims) {
        return jwtAuthProvider.generateAccessToken(claims);
    }

    private String generateRefreshToken(Map<String, Object> claims, Instant refreshTokenExpiration) {
        return jwtAuthProvider.generateRefreshToken(claims, refreshTokenExpiration);
    }

    private void saveRefreshToken(Long userId, String refreshToken, Instant expiredAt) {
        AddRefreshTokenCommand command = new AddRefreshTokenCommand(new User.UserId(userId), refreshToken, expiredAt);
        addRefreshTokenUseCase.addRefreshToken(command);
    }

    private void sendTokensInResponse(HttpServletResponse response, String accessToken, String refreshToken) throws IOException {
        LoginResponse responseBody = new LoginResponse(accessToken, refreshToken);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
    }

}
