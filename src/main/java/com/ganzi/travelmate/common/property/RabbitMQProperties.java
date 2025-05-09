package com.ganzi.travelmate.common.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.rabbitmq")
@RequiredArgsConstructor
@Getter
public class RabbitMQProperties {
    private final String host;
    private final int port;
    private final String username;
    private final String password;
}
