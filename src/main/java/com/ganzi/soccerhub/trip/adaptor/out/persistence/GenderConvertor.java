package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.convertor.AbstractEnumAttributeConvertor;
import com.ganzi.soccerhub.user.domain.Gender;
import jakarta.persistence.Converter;

@Converter
class GenderConvertor extends AbstractEnumAttributeConvertor<Gender> {
    static final String ENUM_NAME = "성별";

    GenderConvertor() {
        super(Gender.class, false, GenderConvertor.ENUM_NAME);
    }
}
