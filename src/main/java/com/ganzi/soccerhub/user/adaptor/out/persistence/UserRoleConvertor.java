package com.ganzi.soccerhub.user.adaptor.out.persistence;

import com.ganzi.soccerhub.common.convertor.AbstractEnumAttributeConvertor;
import com.ganzi.soccerhub.user.domain.UserRole;
import jakarta.persistence.Converter;

@Converter
class UserRoleConvertor extends AbstractEnumAttributeConvertor<UserRole> {
    static final String ENUM_NAME = "사용자 역할";

    UserRoleConvertor() {
        super(UserRole.class, false, UserRoleConvertor.ENUM_NAME);
    }
}
