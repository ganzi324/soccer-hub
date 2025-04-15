package com.ganzi.soccerhub.trip.application.port.out;

import com.ganzi.soccerhub.trip.domain.RequestStatus;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoadTravelMateJoinRequestPort {
    Optional<TravelMateJoinRequest> loadById(TravelMateJoinRequest.Id id);

    Page<TravelMateJoinRequest> loadByPost(TravelMatePost.PostId postId, RequestStatus status, Pageable pageable);

    Optional<TravelMateJoinRequest> loadByPostIdAndRequesterId(TravelMatePost.PostId postId, User.UserId requestId);
}
