package com.ganzi.travelmate.notification.application.service.factory;

import com.ganzi.travelmate.notification.domain.Notification;
import com.ganzi.travelmate.notification.domain.NotificationType;

import java.util.UUID;

public interface NotificationFactory {
    boolean supports(NotificationType type);
    Notification createNotification(UUID id, Object contextData);
}