package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserDetailsProvider implements UserDetailsService {

    private final GetUserQuery getUserQuery;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserQuery.getUserByEmail(email).orElseThrow(() -> {
            log.info(MessageFormat.format("User id not found. (id : {0})", email));
            return new UsernameNotFoundException(MessageFormat.format("User id not found. (id : {0})", email));
        });
        return AuthUser.of(user);
    }
}
