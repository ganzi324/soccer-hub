package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.auth.dto.OAuthAttributes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OAuthAttributesTest {

    @Test
    @DisplayName("Google 인증 속성 생성")
    void constructGoogleOAuth() {
        String registrationId = "google";
        String userNameAttributeName = "sub";

        Map<String, Object> googleAttributes = getGoogleAttributes();

        OAuthAttributes oAuthAttributes = OAuthAttributes.of(
                registrationId,
                userNameAttributeName,
                googleAttributes
        );

        assertThat(oAuthAttributes.name()).isEqualTo(googleAttributes.get("name"));
        assertThat(oAuthAttributes.email()).isEqualTo(googleAttributes.get("email"));
    }

    @Test
    @DisplayName("Naver 인증 속성 생성")
    void constructNaverOAuth() {
        String registrationId = "naver";
        String userNameAttributeName = "response";

        Map<String, Object> naverAttributes = getNaverAttributes();

        OAuthAttributes oAuthAttributes = OAuthAttributes.of(
                registrationId,
                userNameAttributeName,
                naverAttributes
        );

        Map<String, Object> attributes = (Map<String, Object>) naverAttributes.get("response");

        assertThat(oAuthAttributes.name()).isEqualTo(attributes.get("name"));
        assertThat(oAuthAttributes.email()).isEqualTo(naverAttributes.get("email"));

    }

    @Test
    @DisplayName("미지원 인증 제공자 인증 속성 생성")
    void constructUnsupportedProvider() {
        assertThatThrownBy(() -> OAuthAttributes.of("unsupported_provider", "sub", getGoogleAttributes()))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unsupported provider")
                .hasMessageContaining("unsupported_provider");
    }

    private Map<String, Object> getGoogleAttributes() {
        return Map.of(
                "sub", "sub12345",
                "name", "Kim Ganzi",
                "given_name", "Ganzi",
                "family_name", "Kim",
                "picture", "picture_url",
                "email", "ganzi@gmail.com",
                "email_verified", true
        );
    }

    private Map<String, Object> getNaverAttributes() {
        return Map.of(
                "resultCode", "00",
                "message", "succeess",
                "response", Map.of(
                        "id", "provider_id",
                        "nickname", "nickname",
                        "profile_image", "img_profile",
                        "name", "ganzi"
                )
        );
    }
}
