package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.convertor.AbstractEnumAttributeConvertor;
import com.ganzi.soccerhub.trip.domain.AgeRange;
import jakarta.persistence.Converter;

@Converter
class AgeRangeConvertor extends AbstractEnumAttributeConvertor<AgeRange> {
    static final String ENUM_NAME = "연령";

    AgeRangeConvertor() {
        super(AgeRange.class, false, AgeRangeConvertor.ENUM_NAME);
    }
}
