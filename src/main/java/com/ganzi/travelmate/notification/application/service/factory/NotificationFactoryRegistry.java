package com.ganzi.travelmate.notification.application.service.factory;

import com.ganzi.travelmate.notification.domain.Notification;
import com.ganzi.travelmate.notification.domain.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NotificationFactoryRegistry {

    private final List<NotificationFactory> factories;

    public Notification create(Object contextData, NotificationType type) {
        return factories.stream()
                .filter(factory -> factory.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported notification type: " + type))
                .createNotification(generateId(), contextData);
    }

    private UUID generateId() {
        return UUID.randomUUID();
    }
}