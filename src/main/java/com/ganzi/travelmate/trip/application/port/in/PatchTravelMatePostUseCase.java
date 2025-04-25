package com.ganzi.travelmate.trip.application.port.in;

import com.ganzi.travelmate.trip.application.command.PatchTravelMatePostCommand;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.User;

public interface PatchTravelMatePostUseCase {
    TravelMatePost patch(PatchTravelMatePostCommand command, User.UserId userId);
}
