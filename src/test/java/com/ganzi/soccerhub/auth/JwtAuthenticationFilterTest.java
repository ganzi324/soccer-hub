package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.user.domain.UserRole;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtAuthenticationFilterTest {

    private final JwtAuthProvider jwtAuthProvider = Mockito.mock(JwtAuthProvider.class);

    private JwtAuthenticationFilter jwtFilter;

    private SessionUser sessionUser;

    @BeforeAll
    void setUp() {
        jwtFilter = new JwtAuthenticationFilter(jwtAuthProvider);

        sessionUser = new SessionUser(
                1L,
                "ganzi@gmail.com",
                Set.of(UserRole.USER)
        );
    }

    @Test
    void testFilter_shouldAddAuthorizationHeader() throws Exception {
        givenGetSessionUserWillSucceed();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid_token");

        MockHttpServletResponse response = new MockHttpServletResponse();

        jwtFilter.doFilterInternal(request, response, Mockito.mock(FilterChain.class));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication.getPrincipal()).isInstanceOf(SessionUser.class);

        SessionUser savedSessionUser = (SessionUser) authentication.getPrincipal();
        assertThat(savedSessionUser.id()).isEqualTo(sessionUser.id());
    }

    private void givenGetSessionUserWillSucceed() {
        given(jwtAuthProvider.getSessionUser(anyString()))
                .willReturn(sessionUser);
    }
}
