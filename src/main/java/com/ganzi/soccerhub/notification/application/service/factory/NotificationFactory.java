package com.ganzi.soccerhub.notification.application.service.factory;

import com.ganzi.soccerhub.notification.domain.Notification;
import com.ganzi.soccerhub.notification.domain.NotificationType;

public interface NotificationFactory {
    boolean supports(NotificationType type);
    Notification createNotification(Object contextData);
}