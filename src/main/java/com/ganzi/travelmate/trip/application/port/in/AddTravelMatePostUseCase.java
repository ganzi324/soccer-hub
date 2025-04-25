package com.ganzi.travelmate.trip.application.port.in;

import com.ganzi.travelmate.trip.application.command.AddTravelMatePostCommand;
import com.ganzi.travelmate.trip.domain.TravelMatePost;

public interface AddTravelMatePostUseCase {
    TravelMatePost addTravelMatePost(AddTravelMatePostCommand command);
}
