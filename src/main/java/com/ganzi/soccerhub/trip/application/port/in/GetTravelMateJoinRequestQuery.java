package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.domain.RequestStatus;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.domain.User;

import java.util.List;

public interface GetTravelMateJoinRequestQuery {
    TravelMateJoinRequest getById(TravelMateJoinRequest.Id id);

    List<TravelMateJoinRequest> getByPost(TravelMatePost.PostId postId, RequestStatus status, User.UserId userId);
}
