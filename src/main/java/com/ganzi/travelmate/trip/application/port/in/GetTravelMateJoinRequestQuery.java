package com.ganzi.travelmate.trip.application.port.in;

import com.ganzi.travelmate.trip.domain.RequestStatus;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetTravelMateJoinRequestQuery {
    TravelMateJoinRequest getById(TravelMateJoinRequest.Id id);

    Page<TravelMateJoinRequest> getByPost(TravelMatePost.PostId postId, RequestStatus status, User.UserId userId, Pageable pageable);
}
