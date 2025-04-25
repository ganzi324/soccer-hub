package com.ganzi.travelmate.trip.adaptor.out.persistence;

import com.ganzi.travelmate.common.convertor.AbstractEnumAttributeConvertor;
import com.ganzi.travelmate.trip.domain.TravelMatePostStatus;
import jakarta.persistence.Converter;

@Converter
class TravelMatePostStatusConvertor extends AbstractEnumAttributeConvertor<TravelMatePostStatus> {
    static final String ENUM_NAME = "포스팅 상태";

    TravelMatePostStatusConvertor() {
        super(TravelMatePostStatus.class, false, TravelMatePostStatusConvertor.ENUM_NAME);
    }
}
