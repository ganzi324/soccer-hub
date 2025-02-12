package com.ganzi.soccerhub.auth.application.response;

import com.ganzi.soccerhub.user.application.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private UserResponse user;
}
