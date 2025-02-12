package com.ganzi.soccerhub.auth.adaptor.in.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class LoginRequest {

    private final String email;

    private final String password;
}
