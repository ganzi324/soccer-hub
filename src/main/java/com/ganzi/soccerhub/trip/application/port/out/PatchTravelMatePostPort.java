package com.ganzi.soccerhub.trip.application.port.out;

import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.trip.domain.TravelMatePostStatus;

public interface PatchTravelMatePostPort {
    TravelMatePost patch(TravelMatePost travelMatePost);
    int updateStatus(TravelMatePost.PostId postId, TravelMatePostStatus newStatus);
}
