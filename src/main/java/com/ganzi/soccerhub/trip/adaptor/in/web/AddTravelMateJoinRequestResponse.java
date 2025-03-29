package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.ganzi.soccerhub.common.infra.hashid.HashId;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddTravelMateJoinRequestResponse {

    @HashId
    private Long id;

    public static AddTravelMateJoinRequestResponse of(TravelMateJoinRequest.Id id) {
        return new AddTravelMateJoinRequestResponse(id.value());
    }
}
