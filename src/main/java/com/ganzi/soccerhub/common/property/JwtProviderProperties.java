package com.ganzi.soccerhub.common.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@RequiredArgsConstructor
@Getter
public class JwtProviderProperties {
    private final String secretKey;
    private final long accessExpiredTime;
    private final long refreshExpiredTime;
}
