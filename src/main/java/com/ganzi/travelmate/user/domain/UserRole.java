package com.ganzi.travelmate.user.domain;

import com.ganzi.travelmate.common.convertor.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole implements CommonType {

    GUEST("GUEST", "게스트"),
    USER("USER", "일반 사용자"),
    ADMIN("ADMIN", "관리자");

    private final String code;
    private final String title;

    public static UserRole fromCode(String code) {
        for (UserRole role : values()) {
            if (role.getCode().equals(code)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role code: " + code);
    }
}
