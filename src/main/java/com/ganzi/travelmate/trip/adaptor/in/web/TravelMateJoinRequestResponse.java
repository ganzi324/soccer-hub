package com.ganzi.travelmate.trip.adaptor.in.web;

import com.ganzi.travelmate.common.infra.hashid.HashId;
import com.ganzi.travelmate.trip.domain.RequestStatus;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.trip.domain.TravelMatePostStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TravelMateJoinRequestResponse {

    @HashId("id")
    private final Long id;

    private final TravelMatePostInfo travelMatePostInfo;

    private final String message;

    private final RequestStatus status;

    private final LocalDateTime createdAt;

    public static TravelMateJoinRequestResponse of(TravelMateJoinRequest travelMateJoinRequest) {
        return new TravelMateJoinRequestResponse(
                travelMateJoinRequest.getId().get().value(),
                TravelMatePostInfo.of(travelMateJoinRequest.getTravelMatePost()),
                travelMateJoinRequest.getMessage(),
                travelMateJoinRequest.getStatus(),
                travelMateJoinRequest.getCreatedAt()
        );
    }

    record TravelMatePostInfo(
            Long id,
            String title,
            LocalDateTime startDate,
            LocalDateTime endDate,
            TravelMatePostStatus status
    ) {
        static TravelMatePostInfo of(TravelMatePost travelMatePost) {
            return new TravelMatePostInfo(
                    travelMatePost.getId().get().value(),
                    travelMatePost.getTitle(),
                    travelMatePost.getStartDate(),
                    travelMatePost.getEndDate(),
                    travelMatePost.getStatus()
            );
        }
    }
}
