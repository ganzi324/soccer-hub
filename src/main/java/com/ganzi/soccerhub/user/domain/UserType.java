package com.ganzi.soccerhub.user.domain;

import com.ganzi.soccerhub.common.convertor.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserType implements CommonType {

    NORMAL("NORMAL", "일반"),
    GOOGLE("GOOGLE", "구글"),
    NAVER("NAVER", "네이버");

    private final String code;
    private final String name;

    public static UserType from(String provider) {
        return Arrays.stream(UserType.values()).filter(userType -> userType.getCode().equals(provider.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
