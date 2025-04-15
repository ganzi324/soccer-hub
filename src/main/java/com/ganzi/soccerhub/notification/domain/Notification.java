package com.ganzi.soccerhub.notification.domain;

import java.util.UUID;

public interface Notification {
    UUID getId();
    NotificationType getType();
    Target getTarget();
    Content getContent();
}
