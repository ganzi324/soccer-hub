package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;

public interface GetTravelMateJoinRequestQuery {
    TravelMateJoinRequest getById(TravelMateJoinRequest.Id id);
}
