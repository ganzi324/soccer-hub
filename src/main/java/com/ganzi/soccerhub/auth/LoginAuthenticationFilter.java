package com.ganzi.soccerhub.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.soccerhub.auth.application.command.AddRefreshTokenCommand;
import com.ganzi.soccerhub.auth.application.port.in.AddRefreshTokenUseCase;
import com.ganzi.soccerhub.user.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String DEFAULT_LOGIN_URL = "/v1/auth/login";

    private final AuthenticationManager authenticationManager;
    private final JwtAuthProvider jwtAuthProvider;
    private final AddRefreshTokenUseCase addRefreshTokenUseCase;

    public LoginAuthenticationFilter(AuthenticationManager authenticationManager, JwtAuthProvider jwtAuthProvider, AddRefreshTokenUseCase addRefreshTokenUseCase) {
        this.jwtAuthProvider = jwtAuthProvider;
        this.authenticationManager = authenticationManager;
        this.addRefreshTokenUseCase = addRefreshTokenUseCase;

        super.setFilterProcessesUrl(DEFAULT_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),
                    loginRequest.password()
            );

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            // TODO : 파싱에러 어떻게 처리?
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthUser authUser = (AuthUser) authResult.getPrincipal();

        Map<String, Object> claims = createClaims(authUser);
        String accessToken = generateAccessToken(claims);

        Instant refreshTokenExpiration = jwtAuthProvider.getRefreshTokenExpiresAt();
        String refreshToken = generateRefreshToken(claims, refreshTokenExpiration);

        saveRefreshToken(authUser.getId(), refreshToken, refreshTokenExpiration);

        sendTokensInResponse(response, accessToken, refreshToken);
    }

    private Map<String, Object> createClaims(AuthUser authUser) {
        return Map.of(
                JwtAuthProvider.AUDIENCE, authUser.getId(),
                JwtAuthProvider.USERNAME, authUser.getUsername(),
                JwtAuthProvider.AUTHORITY, JwtClaimConverter.extractRoleNames(authUser.getAuthorities())
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
