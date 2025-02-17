package com.ganzi.soccerhub.user.adaptor.out.persistence;

import com.ganzi.soccerhub.common.convertor.AbstractEnumAttributeConvertor;
import com.ganzi.soccerhub.user.domain.UserRole;
import com.ganzi.soccerhub.user.domain.UserType;
import jakarta.persistence.Converter;

@Converter
class UserTypeConvertor extends AbstractEnumAttributeConvertor<UserType> {
    static final String ENUM_NAME = "사용자 가입 타입";

    UserTypeConvertor() {
        super(UserType.class, false, UserTypeConvertor.ENUM_NAME);
    }
}
