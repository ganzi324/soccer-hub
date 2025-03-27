package com.ganzi.soccerhub.trip.domain;

import com.ganzi.soccerhub.common.convertor.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TravelMatePostStatus implements CommonType {

    DRAFT("DRAFT", "임시 저장"),
    OPEN("OPEN", "모집 중"),
    CLOSED("CLOSED", "모집 완료"),
    PENDING("PENDING", "모집 대기");

    private final String code;
    private final String description;
}

