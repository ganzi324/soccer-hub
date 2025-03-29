package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.convertor.AbstractEnumAttributeConvertor;
import com.ganzi.soccerhub.trip.domain.RequestStatus;
import jakarta.persistence.Converter;

@Converter
class RequestStatusConvertor extends AbstractEnumAttributeConvertor<RequestStatus> {
    static final String ENUM_NAME = "참여요청 상태";

    RequestStatusConvertor() {
        super(RequestStatus.class, false, RequestStatusConvertor.ENUM_NAME);
    }
}
