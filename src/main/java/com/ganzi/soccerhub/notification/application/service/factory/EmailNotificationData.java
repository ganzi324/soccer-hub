package com.ganzi.soccerhub.notification.application.service.factory;

import java.util.List;

public record EmailNotificationData(String subject, String body, List<String> to, List<String> cc, List<String> bcc) {}
