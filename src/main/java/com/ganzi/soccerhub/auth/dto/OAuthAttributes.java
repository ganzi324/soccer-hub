package com.ganzi.soccerhub.auth.dto;

import com.ganzi.soccerhub.user.domain.UserType;
import lombok.Builder;

import java.util.Map;

@Builder
public record OAuthAttributes(
        Map<String, Object> attributes,
        String registrationId,
        String name,
        String email,
        String picture
) {

    public static OAuthAttributes of(
            String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        if (UserType.from(registrationId).equals(UserType.GOOGLE)) {
            return ofGoogle(registrationId, userNameAttributeName, attributes);
        }
        if (UserType.from(registrationId).equals(UserType.NAVER)) {
            return ofNaver(registrationId, userNameAttributeName, attributes);
        }

        throw new IllegalStateException("Unexpected value: " + registrationId);
    }

    private static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .registrationId(registrationId)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .build();
    }

    private static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get(userNameAttributeName);

        return OAuthAttributes.builder()
                .registrationId(registrationId)
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .build();
    }

}