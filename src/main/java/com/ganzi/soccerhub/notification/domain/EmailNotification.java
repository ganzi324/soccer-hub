package com.ganzi.soccerhub.notification.domain;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class EmailNotification implements Notification {

    private final UUID id;
    private final EmailReceiver receiver;
    private final EmailContent content;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override
    public Content getContent() {
        return content;
    }

    @Override
    public Target getTarget() {
        return receiver;
    }
}
