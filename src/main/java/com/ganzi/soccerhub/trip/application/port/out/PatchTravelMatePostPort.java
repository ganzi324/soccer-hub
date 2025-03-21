package com.ganzi.soccerhub.trip.application.port.out;

import com.ganzi.soccerhub.trip.domain.TravelMatePost;

public interface PatchTravelMatePostPort {
    TravelMatePost patch(TravelMatePost travelMatePost);
}
