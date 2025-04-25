package com.ganzi.travelmate.trip.adaptor.in.web;

import com.ganzi.travelmate.common.infra.hashid.HashId;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;
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
