package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.user.domain.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class JwtClaimConverter {
    public static Set<String> extractRoleNames(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    public static Set<String> roleNamesFromUserRoles(Set<UserRole> userRoles) {
        return userRoles.stream()
                .map(UserRole::getCode)
                .collect(Collectors.toSet());
    }

    public static Set<UserRole> namesToRoles(List<String> roleNames) {
        return roleNames.stream()
                .map(UserRole::fromCode)
                .collect(Collectors.toSet());
    }

    public static List<GrantedAuthority> toGrantedAuthorities(Set<String> roleNames) {
        return roleNames.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static List<GrantedAuthority> rolesToGrantedAuthorities(Set<UserRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
    }
}
