package com.ganzi.soccerhub.auth.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.soccerhub.auth.JwtAuthProvider;
import com.ganzi.soccerhub.auth.application.port.in.GetRefreshTokenQuery;
import com.ganzi.soccerhub.auth.application.port.in.RenewAccessTokenUseCase;
import com.ganzi.soccerhub.auth.application.port.out.AddRefreshTokenPort;
import com.ganzi.soccerhub.auth.application.result.AuthenticationTokens;
import com.ganzi.soccerhub.common.RefreshTokenTestData;
import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.common.property.JwtProviderProperties;
import com.ganzi.soccerhub.user.application.command.GetRefreshTokenCommand;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JwtAuthProvider.class, RefreshTokenServiceTest.TestConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {
        "jwt.secret_key=683996FAE6DA6C33CC5CAEAE6A6A2D3SDDFC3DFEBABCDA3F9",
        "jwt.access_expired_time=30",
        "jwt.refresh_expired_time=60"
})
class RefreshTokenServiceTest {

    @TestConfiguration
    static class TestConfig {
        @Value("${jwt.secret_key}")
        private String secretKey;
        @Value("${jwt.access_expired_time}")
        private long accessExpiredTime;
        @Value("${jwt.refresh_expired_time}")
        private long refreshExpiredTime;

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

        @Bean
        public JwtProviderProperties jwtProviderProperties() {
            return new JwtProviderProperties(secretKey, accessExpiredTime, refreshExpiredTime);
        }

        @Bean
        public JwtAuthProvider jwtAuthProvider() {
            return new JwtAuthProvider(jwtProviderProperties(), objectMapper());
        }
    }

    @Autowired
    private JwtAuthProvider jwtAuthProvider;

    private final GetUserQuery getUserQuery = Mockito.mock(GetUserQuery.class);
    private final GetRefreshTokenQuery getRefreshTokenQuery = Mockito.mock(GetRefreshTokenQuery.class);
    private final AddRefreshTokenPort addRefreshTokenPort = Mockito.mock(AddRefreshTokenPort.class);

    private RenewAccessTokenUseCase renewAccessTokenUseCase;

    private String validRefreshToken;

    @BeforeAll
    void setUp() {
         renewAccessTokenUseCase = new RenewAccessTokenService(
                jwtAuthProvider,
                getUserQuery,
                getRefreshTokenQuery,
                addRefreshTokenPort
        );

        Map<String, Object> claims = jwtAuthProvider.getClaimsByUser(UserTestData.defaultUser());
        validRefreshToken = jwtAuthProvider.generateRefreshToken(claims);
    }

    @Test
    void renewAccessTokenWillSucceed() {
        // given
        User user = UserTestData.defaultUser();

        givenLoadUserWillSucceed(user);
        givenLoadRefreshTokenWillSucceed();

        AuthenticationTokens authTokens = renewAccessTokenUseCase.execute(validRefreshToken);

        assertThat(jwtAuthProvider.getSessionUser(authTokens.refreshToken()).email()).isEqualTo(user.getEmail());
    }

    private void givenLoadUserWillSucceed(User user) {
        given(getUserQuery.getUserById(any(User.UserId.class)))
                .willReturn(Optional.of(user));
    }

    private void givenLoadRefreshTokenWillSucceed() {
        given(getRefreshTokenQuery.getRefreshToken(any(GetRefreshTokenCommand.class)))
                .willReturn(Optional.of(RefreshTokenTestData.defaultRefreshToken(validRefreshToken)));
    }
}
