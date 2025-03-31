package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.domain.RequestStatus;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetTravelMateJoinRequestQuery {
    TravelMateJoinRequest getById(TravelMateJoinRequest.Id id);

    Page<TravelMateJoinRequest> getByPost(TravelMatePost.PostId postId, RequestStatus status, User.UserId userId, Pageable pageable);
}
