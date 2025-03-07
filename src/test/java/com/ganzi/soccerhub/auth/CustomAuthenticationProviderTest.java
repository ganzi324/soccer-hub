package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomAuthenticationProviderTest {

    private final UserDetailsService userDetailsService = Mockito.mock(UserDetailsService.class);

    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    private AuthenticationProvider authenticationProvider;

    @BeforeAll
    void setUp() {
        authenticationProvider = new CustomAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Test
    void authenticate() {
        User user = UserTestData.defaultUser();

        givenLoadUserWillSucceed(user);
        givenPasswordMatchWillSucceed();

        UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(generateAuthenticationByUser(user)).getPrincipal();

        assertThat(userDetails).isInstanceOf(AuthUser.class);
        assertThat(userDetails.getUsername()).isEqualTo(user.getEmail());
        assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
    }

    private void givenLoadUserWillSucceed(User user) {
        given(userDetailsService.loadUserByUsername(user.getName()))
                .willReturn(AuthUser.of(user));
    }

    private void givenPasswordMatchWillSucceed() {
        given(passwordEncoder.matches(anyString(), anyString()))
                .willReturn(true);
    }

    private Authentication generateAuthenticationByUser(User user) {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public Object getCredentials() {
                return user.getPassword();
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return user.getName();
            }
        };
    }
}
