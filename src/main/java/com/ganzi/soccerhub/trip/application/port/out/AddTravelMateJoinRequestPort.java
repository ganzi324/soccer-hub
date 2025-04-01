package com.ganzi.soccerhub.trip.application.port.out;

import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;

public interface AddTravelMateJoinRequestPort {
    TravelMateJoinRequest add(TravelMateJoinRequest travelMateJoinRequest);
}
