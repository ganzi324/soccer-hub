package com.ganzi.travelmate.trip.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.common.exception.DomainIdNotFoundException;
import com.ganzi.travelmate.common.exception.UnauthorizedException;
import com.ganzi.travelmate.trip.application.port.in.GetTravelMateJoinRequestQuery;
import com.ganzi.travelmate.trip.application.port.out.LoadTravelMateJoinRequestPort;
import com.ganzi.travelmate.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.travelmate.trip.domain.RequestStatus;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetTravelMateJoinRequestService implements GetTravelMateJoinRequestQuery {

    private final LoadTravelMateJoinRequestPort loadTravelMateJoinRequestPort;
    private final LoadTravelMatePostPort loadTravelMatePostPort;

    @Override
    public TravelMateJoinRequest getById(TravelMateJoinRequest.Id id) {
        return loadTravelMateJoinRequestPort.loadById(id)
                .orElseThrow(() -> new DomainIdNotFoundException(TravelMateJoinRequest.class));
    }

    @Override
    public Page<TravelMateJoinRequest> getByPost(TravelMatePost.PostId postId, RequestStatus status, User.UserId userId, Pageable pageable) {
        TravelMatePost travelMatePost = loadTravelMatePostPort.loadById(postId)
                .orElseThrow(() -> new DomainIdNotFoundException(TravelMatePost.class));

        if (!travelMatePost.getAuthor().getId().get().equals(userId)) {
            throw new UnauthorizedException();
        }

        return loadTravelMateJoinRequestPort.loadByPost(travelMatePost.getId().get(), status, pageable);
    }
}
