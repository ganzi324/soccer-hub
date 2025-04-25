package com.ganzi.travelmate.auth;

import com.ganzi.travelmate.user.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUser implements UserDetails {

    private final User user;

    public static AuthUser of(User user) {
        return new AuthUser(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getUserRole().getCode()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public Long getId() {
        return user.getId().get().value();
    }
}
