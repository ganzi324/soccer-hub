package com.ganzi.soccerhub.trip.application.port.out;

import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;

import java.util.Optional;

public interface LoadTravelMateJoinRequestPort {
    Optional<TravelMateJoinRequest> loadById(TravelMateJoinRequest.Id id);
}
