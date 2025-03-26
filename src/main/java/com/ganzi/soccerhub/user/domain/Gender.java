package com.ganzi.soccerhub.user.domain;

import com.ganzi.soccerhub.common.convertor.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender implements CommonType {

    MALE("MALE", "남"),
    FEMALE("FEMALE", "여"),
    ANY("ANY", "제한 없음");

    private final String code;
    private final String description;

    public static Gender fromCode(String code) {
        for (Gender gender : values()) {
            if (gender.getCode().equals(code)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender code: " + code);
    }
}
