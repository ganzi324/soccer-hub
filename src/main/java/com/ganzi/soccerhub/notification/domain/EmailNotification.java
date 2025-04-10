package com.ganzi.soccerhub.notification.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailNotification implements Notification {

    private final EmailReceiver receiver;
    private final EmailContent content;

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
