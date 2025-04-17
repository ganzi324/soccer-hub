package com.ganzi.soccerhub.notification.domain;

public record EmailContent(String subject, String body) implements Content {}
