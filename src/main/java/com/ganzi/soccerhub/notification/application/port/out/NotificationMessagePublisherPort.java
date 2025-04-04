package com.ganzi.soccerhub.notification.application.port.out;

import com.ganzi.soccerhub.notification.domain.Notification;

public interface NotificationMessagePublisherPort {
    void publish(Notification notification);
}
