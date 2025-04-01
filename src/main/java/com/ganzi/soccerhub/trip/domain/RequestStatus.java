package com.ganzi.soccerhub.trip.domain;

import com.ganzi.soccerhub.common.convertor.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RequestStatus implements CommonType {

    PENDING("PENDING", "보류"),
    APPROVED("APPROVED", "승인"),
    REJECTED("REJECTED", "거절");

    private final String code;
    private final String description;
}
