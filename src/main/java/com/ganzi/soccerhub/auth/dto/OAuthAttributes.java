package com.ganzi.soccerhub.auth.dto;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

@Builder
public record OAuthAttributes(
        Map<String, Object> attributes,
        String registrationId,
        String providerId,
        String name,
        String email,
        String picture
) {

    public static OAuthAttributes of(
            String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        return switch (registrationId.toLowerCase()) {
            case "google" -> ofGoogle(registrationId, userNameAttributeName, attributes);
            case "naver" -> ofNaver(registrationId, userNameAttributeName, attributes);
            default -> throw new IllegalArgumentException("Unsupported provider: " + registrationId);
        };
    }

    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("providerId", providerId);
        attributes.put("registrationId", registrationId);
        attributes.put("name", name);
        attributes.put("email", email);
        attributes.put("picture", picture);

        return attributes;
    }

    private static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .registrationId(registrationId)
                .providerId((String) attributes.get("sub"))
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
                .providerId((String) response.get("id"))
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .build();
    }

}