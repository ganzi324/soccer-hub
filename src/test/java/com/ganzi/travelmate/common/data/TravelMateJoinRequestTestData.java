package com.ganzi.travelmate.common.data;

import com.ganzi.travelmate.common.UserTestData;
import com.ganzi.travelmate.trip.domain.RequestStatus;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;

import java.time.LocalDateTime;

public class TravelMateJoinRequestTestData {

    public static TravelMateJoinRequest pending() {
        return TravelMateJoinRequest.withId(
                TravelMateJoinRequest.Id.of(1L),
                TravelMatePostTestData.defaultPost(),
                UserTestData.defaultUser(),
                "저도 꽃피는 봄날 여행을 가고 싶어요.",
                RequestStatus.PENDING,
                LocalDateTime.now().minusDays(1)
        );
    }

    public static TravelMateJoinRequest pendingSame() {
        return TravelMateJoinRequest.withId(
                TravelMateJoinRequest.Id.of(1L),
                TravelMatePostTestData.defaultPost(),
                UserTestData.defaultUser(),
                "가고 싶다 여행.",
                RequestStatus.PENDING,
                LocalDateTime.now().minusDays(2)
        );
    }

    public static TravelMateJoinRequest approved() {
        return TravelMateJoinRequest.withId(
                TravelMateJoinRequest.Id.of(2L),
                TravelMatePostTestData.defaultPost(),
                UserTestData.defaultUser(),
                "저도 꽃피는 봄날 여행을 가고 싶어요.",
                RequestStatus.APPROVED,
                LocalDateTime.now().minusDays(2)
        );
    }
}
