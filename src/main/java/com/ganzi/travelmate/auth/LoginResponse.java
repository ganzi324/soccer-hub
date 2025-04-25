package com.ganzi.travelmate.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
}
