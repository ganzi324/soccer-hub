package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.trip.domain.TravelMatePostStatus;
import com.ganzi.soccerhub.user.domain.User;

public interface ChangeTravelMatePostStatusUseCase {
    TravelMatePost changeStatus(TravelMatePostStatus newStatus, TravelMatePost.PostId postId, User.UserId userId);
}
