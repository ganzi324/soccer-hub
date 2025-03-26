package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.application.command.PatchTravelMatePostCommand;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.domain.User;

public interface PatchTravelMatePostUseCase {
    TravelMatePost patch(PatchTravelMatePostCommand command, User.UserId userId);
}
