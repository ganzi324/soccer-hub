package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsProvider implements UserDetailsService {

    private final GetUserQuery getUserQuery;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserQuery.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email이 존재하지 않음"));
        return AuthUser.of(user);
    }
}
