package com.ganzi.soccerhub.trip.application.port.out;

import com.ganzi.soccerhub.trip.domain.TravelMatePost;

import java.util.Optional;

public interface LoadTravelMatePostPort {

    Optional<TravelMatePost> loadById(TravelMatePost.PostId id);
}
