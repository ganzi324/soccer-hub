package com.ganzi.travelmate.notification.application.port.in;

import com.ganzi.travelmate.notification.domain.Notification;

public interface SendNotificationUseCase {
    void send(Notification notification);
}
