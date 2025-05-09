package com.ganzi.travelmate.trip.application.port.out;

import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;

public interface AddTravelMateJoinRequestPort {
    TravelMateJoinRequest add(TravelMateJoinRequest travelMateJoinRequest);
}
