package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.notification.application.exception.UnsupportedNotificationTypeException;
import com.ganzi.soccerhub.notification.application.service.factory.EmailNotificationData;
import com.ganzi.soccerhub.notification.application.service.factory.NotificationFactory;
import com.ganzi.soccerhub.notification.domain.Notification;
import com.ganzi.soccerhub.notification.domain.NotificationType;
import com.ganzi.soccerhub.place.domain.Place;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TravelMateJoinRequestNotificationGenerator {
    private static final String TEMPLATE_PATH = "travel-mate-request-email";
    private static final String EMAIL_TITLE = "여행 동행 요청 알림";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy년M월d일");

    private final List<NotificationFactory> notificationFactories;
    private final TemplateEngine templateEngine;

    Notification generate(TravelMateJoinRequest travelMateJoinRequest, NotificationType type) {
        return notificationFactories.stream()
                .filter(factory -> factory.supports(type))
                .findFirst()
                .map(factory -> factory.createNotification(createNotificationData(travelMateJoinRequest)))
                .orElseThrow(() -> new UnsupportedNotificationTypeException("Unsupported notification type: " + type));
    }

    private EmailNotificationData createNotificationData(TravelMateJoinRequest travelMateJoinRequest) {
        Context context = new Context();
        Map.of(
                "postTitle", travelMateJoinRequest.getTravelMatePost().getTitle(),
                "requesterName", travelMateJoinRequest.getRequester().getName(),
                "requestTime", travelMateJoinRequest.getCreatedAt().format(FORMATTER),
                "travelPeriod", MessageFormat.format("{0} ~ {1}", travelMateJoinRequest.getTravelMatePost().getStartDate().format(FORMATTER), travelMateJoinRequest.getTravelMatePost().getEndDate().format(FORMATTER)),
                "travelLocation", travelMateJoinRequest.getTravelMatePost().getPlaces()
                        .stream()
                        .map(Place::getName)
                        .collect(Collectors.joining(", ")),
                "numberOfPeople", travelMateJoinRequest.getTravelMatePost().getCapacity(),
                "gender", travelMateJoinRequest.getTravelMatePost().getGender(),
                "ageGroup", travelMateJoinRequest.getTravelMatePost().getAge(),
                "postUrl", ""
        ).forEach(context::setVariable);

        String content = templateEngine.process(TEMPLATE_PATH, context);

        return new EmailNotificationData(
                EMAIL_TITLE,
                content,
                List.of(travelMateJoinRequest.getTravelMatePost().getAuthor().getEmail()),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}
