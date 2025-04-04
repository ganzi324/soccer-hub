package com.ganzi.soccerhub.common.infra.message;

import com.ganzi.soccerhub.common.property.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMQProperties properties;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("notification-exchange");
    }

    @Bean
    public Queue notificationQueue() {
        return QueueBuilder.durable("notification-queue")
                .withArgument("x-dead-letter-exchange", "notification-exchange")
                .withArgument("x-dead-letter-routing-key", "notification.dlq")
                .build();
    }

    @Bean
    public Queue notificationDLQ() {
        return QueueBuilder.durable("notification-dlq").build();
    }

    @Bean
    public Binding notificationQueueBinding(DirectExchange exchange, Queue notificationQueue) {
        return BindingBuilder.bind(notificationQueue).to(exchange).with("notification.send");
    }

    @Bean
    public Binding notificationDLQBinding(DirectExchange exchange, Queue notificationDLQ) {
        return BindingBuilder.bind(notificationDLQ).to(exchange).with("notification.dlq");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(properties.getHost());
        connectionFactory.setPort(properties.getPort());
        connectionFactory.setUsername(properties.getUsername());
        connectionFactory.setPassword(properties.getPassword());
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
