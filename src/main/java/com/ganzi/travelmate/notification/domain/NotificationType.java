package com.ganzi.travelmate.notification.domain;

import com.ganzi.travelmate.common.convertor.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NotificationType implements CommonType {

    EMAIL("EMAIL", "이메일");

    private final String code;
    private final String description;
}
