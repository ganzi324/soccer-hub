package com.ganzi.travelmate.user.adaptor.out.persistence;

import com.ganzi.travelmate.common.convertor.AbstractEnumAttributeConvertor;
import com.ganzi.travelmate.user.domain.UserRole;
import jakarta.persistence.Converter;

@Converter
class UserRoleConvertor extends AbstractEnumAttributeConvertor<UserRole> {
    static final String ENUM_NAME = "사용자 역할";

    UserRoleConvertor() {
        super(UserRole.class, false, UserRoleConvertor.ENUM_NAME);
    }
}
