package com.ganzi.soccerhub.notification.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.notification.application.port.in.SendNotificationUseCase;
import com.ganzi.soccerhub.notification.application.port.out.NotificationMessagePublisherPort;
import com.ganzi.soccerhub.notification.domain.Notification;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class NotificationService implements SendNotificationUseCase {

    private final NotificationMessagePublisherPort notificationMessagePublisher;

    @Override
    public void send(Notification notification) {
        notificationMessagePublisher.publish(notification);
    }
}
