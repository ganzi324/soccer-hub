package com.ganzi.soccerhub.user.domain;

import com.ganzi.soccerhub.common.convertor.CommonType;
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
}
