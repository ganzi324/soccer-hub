package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.notification.application.port.in.SendNotificationUseCase;
import com.ganzi.soccerhub.notification.domain.Notification;
import com.ganzi.soccerhub.notification.domain.NotificationType;
import com.ganzi.soccerhub.trip.application.event.TravelMateJoinRequestCreatedEvent;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelMateJoinRequestEventListener {

    private final SendNotificationUseCase sendNotificationUseCase;
    private final TravelMateJoinRequestNotificationGenerator generator;

    @EventListener
    @Async
    public void handleTravelMateJoinRequestCreatedEvent(TravelMateJoinRequestCreatedEvent event) {
        sendNotification(event.travelMateJoinRequest(), NotificationType.EMAIL);
    }

    private void sendNotification(TravelMateJoinRequest travelMateJoinRequest, NotificationType type) {
        Notification notification = generator.generate(travelMateJoinRequest, type);
        sendNotificationUseCase.send(notification);
    }
}
