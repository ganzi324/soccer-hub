package com.ganzi.soccerhub.notification.application.service.factory;

import com.ganzi.soccerhub.notification.application.exception.InvalidNotificationContextException;
import com.ganzi.soccerhub.notification.domain.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmailNotificationFactory implements NotificationFactory {

    @Override
    public boolean supports(NotificationType type) {
        return type == NotificationType.EMAIL;
    }

    @Override
    public Notification createNotification(UUID id, Object contextData) {
        if (!(contextData instanceof EmailNotificationData emailNotificationData)) {
            throw new InvalidNotificationContextException(
                    "Invalid notification context: Expected EmailNotificationData but received "
                    + contextData.getClass().getSimpleName());
        }

        EmailReceiver receiver = new EmailReceiver(emailNotificationData.to(), emailNotificationData.cc(), emailNotificationData.bcc());
        EmailContent emailContent = new EmailContent(emailNotificationData.subject(), emailNotificationData.body());

        return new EmailNotification(id, receiver, emailContent);
    }
}
