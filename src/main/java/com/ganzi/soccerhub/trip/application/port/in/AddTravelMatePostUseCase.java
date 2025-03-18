package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.application.command.AddTravelMatePostCommand;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;

public interface AddTravelMatePostUseCase {
    TravelMatePost addTravelMatePost(AddTravelMatePostCommand command);
}
