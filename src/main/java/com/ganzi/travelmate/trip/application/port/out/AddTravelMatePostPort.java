package com.ganzi.travelmate.trip.application.port.out;

import com.ganzi.travelmate.trip.domain.TravelMatePost;

public interface AddTravelMatePostPort {

    TravelMatePost addPost(TravelMatePost travelMatePost);
}
