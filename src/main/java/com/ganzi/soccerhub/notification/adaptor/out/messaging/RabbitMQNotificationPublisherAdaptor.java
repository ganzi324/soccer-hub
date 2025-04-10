package com.ganzi.soccerhub.notification.adaptor.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.soccerhub.common.MessageQueueAdapter;
import com.ganzi.soccerhub.common.infra.message.RabbitMQConfig;
import com.ganzi.soccerhub.notification.application.port.out.NotificationMessagePublisherPort;
import com.ganzi.soccerhub.notification.domain.Notification;
import com.ganzi.soccerhub.notification.domain.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@MessageQueueAdapter
@RequiredArgsConstructor
public class RabbitMQNotificationPublisherAdaptor implements NotificationMessagePublisherPort {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(Notification notification) {
        MessageProperties props = new MessageProperties();
        props.setHeader("__TypeId__", getTypeId(notification.getType()));

        try {
            byte[] body = objectMapper.writeValueAsBytes(notification);
            Message message = new Message(body, props);
            rabbitTemplate.send(RabbitMQConfig.NOTIFICATION_EXCHANGE, RabbitMQConfig.ROUTING_KEY_MAIN, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("메시지 직렬화 실패", e);
        }
    }

    private String getTypeId(NotificationType type) {
        return switch (type) {
            case NotificationType.EMAIL -> "emailNotification";
        };
    }
}
