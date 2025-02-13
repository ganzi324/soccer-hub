package com.ganzi.soccerhub.auth.application.service;

import com.ganzi.soccerhub.auth.JwtAuthProvider;
import com.ganzi.soccerhub.auth.application.command.LoginCommand;
import com.ganzi.soccerhub.auth.application.port.in.LoginUseCase;
import com.ganzi.soccerhub.auth.application.response.LoginResponse;
import com.ganzi.soccerhub.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final JwtAuthProvider jwtAuthProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse execute(LoginCommand command) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.getEmail(), command.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, String> claims = Map.of(
                JwtAuthProvider.AUDIENCE, userDetails.getUsername(),
                JwtAuthProvider.AUTHORITY, userDetails.getAuthorities().toString()
        );

        String accessToken = this.jwtAuthProvider.generateAccessToken(claims);

        return new LoginResponse(
                accessToken,
                null
        );
    }

}
