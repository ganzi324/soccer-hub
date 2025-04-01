package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.application.command.AddTravelMateJoinRequestCommand;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;

public interface AddTravelMateJoinRequestUseCase {
    TravelMateJoinRequest add(AddTravelMateJoinRequestCommand command);
}
