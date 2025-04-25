package com.ganzi.travelmate.notification.domain;

import java.util.UUID;

public interface Notification {
    UUID getId();
    NotificationType getType();
    Target getTarget();
    Content getContent();
}
