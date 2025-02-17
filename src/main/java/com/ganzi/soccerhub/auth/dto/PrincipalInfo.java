package com.ganzi.soccerhub.auth.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public record PrincipalInfo(
        Set<GrantedAuthority> authorities,
        Map<String, Object> attributes,
        String nameAttributeKey
) implements OAuth2User {

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.getAttribute("name");
    }
}
