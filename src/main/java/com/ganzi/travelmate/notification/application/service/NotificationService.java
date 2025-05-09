package com.ganzi.travelmate.notification.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.notification.application.port.in.SendNotificationUseCase;
import com.ganzi.travelmate.notification.application.port.out.NotificationMessagePublisherPort;
import com.ganzi.travelmate.notification.domain.Notification;
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
