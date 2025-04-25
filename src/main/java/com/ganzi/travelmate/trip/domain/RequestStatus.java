package com.ganzi.travelmate.trip.domain;

import com.ganzi.travelmate.common.convertor.CommonType;
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
