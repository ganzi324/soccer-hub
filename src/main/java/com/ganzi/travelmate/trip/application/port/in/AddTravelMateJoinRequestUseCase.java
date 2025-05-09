package com.ganzi.travelmate.trip.application.port.in;

import com.ganzi.travelmate.trip.application.command.AddTravelMateJoinRequestCommand;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;

public interface AddTravelMateJoinRequestUseCase {
    TravelMateJoinRequest add(AddTravelMateJoinRequestCommand command);
}
