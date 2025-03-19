package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.domain.TravelMatePost;

import java.util.Optional;

public interface GetTravelMatePostQuery {

    Optional<TravelMatePost> getById(TravelMatePost.PostId id);
}
