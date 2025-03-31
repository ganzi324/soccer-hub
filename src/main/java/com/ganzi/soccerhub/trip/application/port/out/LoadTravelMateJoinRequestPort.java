package com.ganzi.soccerhub.trip.application.port.out;

import com.ganzi.soccerhub.trip.domain.RequestStatus;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;

import java.util.List;
import java.util.Optional;

public interface LoadTravelMateJoinRequestPort {
    Optional<TravelMateJoinRequest> loadById(TravelMateJoinRequest.Id id);

    List<TravelMateJoinRequest> loadByPost(TravelMatePost.PostId postId, RequestStatus status);
}
