package com.ganzi.soccerhub.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String DEFAULT_LOGIN_URL = "/v1/auth/login";

    private final AuthenticationManager authenticationManager;
    private final JwtAuthProvider jwtAuthProvider;

    public LoginAuthenticationFilter(AuthenticationManager authenticationManager, JwtAuthProvider jwtAuthProvider) {
        this.jwtAuthProvider = jwtAuthProvider;
        this.authenticationManager = authenticationManager;

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

        Map<String, Object> claims = Map.of(
                JwtAuthProvider.AUDIENCE, authUser.getId(),
                JwtAuthProvider.EMAIL, authUser.getUsername(),
                JwtAuthProvider.AUTHORITY, JwtClaimConverter.extractRoleNames(authUser.getAuthorities())
        );

        String accessToken = jwtAuthProvider.generateAccessToken(claims);

        LoginResponse responseBody = new LoginResponse(
                accessToken,
                null
        );

        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
    }
}
