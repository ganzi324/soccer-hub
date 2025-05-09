package com.ganzi.travelmate.notification.application.port.out;

import com.ganzi.travelmate.notification.domain.Notification;

public interface NotificationMessagePublisherPort {
    void publish(Notification notification);
}
