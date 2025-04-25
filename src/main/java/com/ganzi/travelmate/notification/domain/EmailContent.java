package com.ganzi.travelmate.notification.domain;

public record EmailContent(String subject, String body) implements Content {}
