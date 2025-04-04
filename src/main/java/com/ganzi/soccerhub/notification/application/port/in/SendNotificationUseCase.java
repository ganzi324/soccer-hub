package com.ganzi.soccerhub.notification.application.port.in;

import com.ganzi.soccerhub.notification.domain.Notification;

public interface SendNotificationUseCase {
    void send(Notification notification);
}
