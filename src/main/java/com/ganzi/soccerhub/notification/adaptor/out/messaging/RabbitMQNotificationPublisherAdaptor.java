package com.ganzi.soccerhub.notification.adaptor.out.messaging;

import com.ganzi.soccerhub.common.MessageQueueAdapter;
import com.ganzi.soccerhub.notification.application.port.out.NotificationMessagePublisherPort;
import com.ganzi.soccerhub.notification.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@MessageQueueAdapter
@RequiredArgsConstructor
public class RabbitMQNotificationPublisherAdaptor implements NotificationMessagePublisherPort {


    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Notification notification) {
        rabbitTemplate.convertAndSend("notification-exchange", "notification.send", notification);
    }
}
