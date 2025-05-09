package com.ganzi.travelmate.auth;

import com.ganzi.travelmate.user.domain.UserRole;

import java.util.Set;

public record SessionUser(Long id, String email, Set<UserRole> userRoles) {

}
