package com.ganzi.travelmate.auth.domain;

import com.ganzi.travelmate.common.RefreshTokenTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class RefreshTokenTest {

    @Test
    @DisplayName("만료일 확인")
    void isExpired() {
        String tokenValue = "refresh_token_value";
        RefreshToken refreshToken = RefreshTokenTestData.expiredRefreshToken(tokenValue);

        assertThat(refreshToken.isExpired()).isTrue();
    }

    @Test
    @DisplayName("토큰 갱신")
    void renewToken() {
        String tokenValue = "refresh_token_value";
        String newTokenValue = "new_refresh_token_value";
        RefreshToken refreshToken = RefreshTokenTestData.expiredRefreshToken(tokenValue);

        RefreshToken renewedRefreshToken = refreshToken.renew(newTokenValue, Instant.now().plusSeconds(100));

        // then
        assertThat(renewedRefreshToken).isNotEqualTo(refreshToken);
        assertThat(renewedRefreshToken.getId()).isEqualTo(refreshToken.getId());
        assertThat(renewedRefreshToken.isExpired()).isFalse();

    }
}
