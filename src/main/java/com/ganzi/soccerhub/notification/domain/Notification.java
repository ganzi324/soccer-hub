package com.ganzi.soccerhub.notification.domain;

public interface Notification {
    NotificationType getType();
    Target getTarget();
    Content getContent();
}
