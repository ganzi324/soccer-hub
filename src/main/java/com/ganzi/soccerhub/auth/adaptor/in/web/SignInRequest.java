package com.ganzi.soccerhub.auth.adaptor.in.web;

import com.ganzi.soccerhub.common.infra.hashid.HashId;

public record SignInRequest(String name, String email, String password, @HashId Long id) {}
