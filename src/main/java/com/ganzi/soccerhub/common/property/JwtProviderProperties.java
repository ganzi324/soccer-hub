package com.ganzi.soccerhub.common.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@RequiredArgsConstructor
@Getter
public class JwtProviderProperties {
    private final String secretKey;
    private final long accessExpiredTime = 60 * 60 * 1000L;
    private final long refreshExpiredTime = 7 * 24 * 60 * 60 * 1000L;
}
