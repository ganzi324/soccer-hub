package com.ganzi.travelmate.trip.application.port.in;

import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.trip.domain.TravelMatePostStatus;
import com.ganzi.travelmate.user.domain.User;

public interface ChangeTravelMatePostStatusUseCase {
    TravelMatePost changeStatus(TravelMatePostStatus newStatus, TravelMatePost.PostId postId, User.UserId userId);
}
