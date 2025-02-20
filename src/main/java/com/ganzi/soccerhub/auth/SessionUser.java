package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.user.domain.UserRole;

import java.util.Set;

public record SessionUser(Long id, String email, Set<UserRole> userRoles) {

}
