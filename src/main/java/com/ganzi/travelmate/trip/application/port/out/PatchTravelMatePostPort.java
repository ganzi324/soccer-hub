package com.ganzi.travelmate.trip.application.port.out;

import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.trip.domain.TravelMatePostStatus;

public interface PatchTravelMatePostPort {
    TravelMatePost patch(TravelMatePost travelMatePost);
    int updateStatus(TravelMatePost.PostId postId, TravelMatePostStatus newStatus);
}
