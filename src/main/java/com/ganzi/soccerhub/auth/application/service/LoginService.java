package com.ganzi.soccerhub.auth.application.service;

import com.ganzi.soccerhub.auth.JwtAuthProvider;
import com.ganzi.soccerhub.auth.application.command.LoginCommand;
import com.ganzi.soccerhub.auth.application.port.in.LoginUseCase;
import com.ganzi.soccerhub.auth.application.response.LoginResponse;
import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.user.application.exception.InvalidPasswordException;
import com.ganzi.soccerhub.user.application.exception.UserNotFoundException;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.application.response.UserResponse;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final GetUserQuery getUserQuery;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAuthProvider jwtAuthProvider;

    @Override
    public LoginResponse execute(LoginCommand command) {
        User user = this.getUserQuery.getUserByEmail(command.getEmail()).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        String accessToken = this.jwtAuthProvider.generateAccessToken(user);

        return new LoginResponse(
                accessToken,
                null,
                UserResponse.of(user)
        );
    }

}
