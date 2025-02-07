package com.ganzi.soccerhub.user.domain;

import com.ganzi.soccerhub.common.convertor.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserType implements CommonType {

    USER_NORMAL("normal", "일반"),
    USER_GOOGLE("google", "구글"),
    USER_NAVER("naver", "네이버");

    private final String code;
    private final String name;

    public static UserType from(String provider) {
        return Arrays.stream(UserType.values()).filter(userType -> userType.getCode().equals(provider))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
