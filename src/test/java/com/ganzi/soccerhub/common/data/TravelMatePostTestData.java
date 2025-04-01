package com.ganzi.soccerhub.common.data;

import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.trip.domain.AgeRange;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.trip.domain.TravelMatePostStatus;
import com.ganzi.soccerhub.user.domain.Gender;

import java.time.LocalDateTime;
import java.util.List;

public class TravelMatePostTestData {

    public static TravelMatePost defaultPost() {
        return TravelMatePost.withId(
                TravelMatePost.PostId.of(1L),
                "함께 여행가요!",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                List.of(PlaceTestData.defaultPlace()),
                5,
                Gender.MALE,
                AgeRange.TWENTIES,
                "남산 여행갈 사람 모집합니다. 남자 2명 함께 여행갔으면 좋겠어요.",
                UserTestData.defaultUser(),
                TravelMatePostStatus.OPEN,
                LocalDateTime.now().minusDays(3L),
                LocalDateTime.now().minusDays(3L)
        );
    }

    public static TravelMatePost withoutId() {
        return TravelMatePost.withId(
                null,
                "함께 여행가요!",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                List.of(PlaceTestData.defaultPlace()),
                5,
                Gender.MALE,
                AgeRange.TWENTIES,
                "남산 여행갈 사람 모집합니다. 남자 2명 함께 여행갔으면 좋겠어요.",
                UserTestData.defaultUser(),
                TravelMatePostStatus.OPEN,
                LocalDateTime.now().minusDays(3L),
                LocalDateTime.now().minusDays(3L)
        );
    }

    public static TravelMatePost somePlaces() {
        return TravelMatePost.withId(
                TravelMatePost.PostId.of(2L),
                "장소 3개를 포함한 포스팅입니다.",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                List.of(PlaceTestData.sample1(), PlaceTestData.sample2(), PlaceTestData.sample3()),
                10,
                Gender.FEMALE,
                AgeRange.THIRTIES,
                "어디든 여행 가자. 지금 날씨 좋다.",
                UserTestData.defaultUser(),
                TravelMatePostStatus.OPEN,
                LocalDateTime.now().minusDays(3L),
                LocalDateTime.now().minusDays(3L)
        );
    }

    public static TravelMatePost closed() {
        return TravelMatePost.withId(
                TravelMatePost.PostId.of(3L),
                "모집이 완료된 포스팅입니다.",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                List.of(PlaceTestData.sample1(), PlaceTestData.sample2(), PlaceTestData.sample3()),
                10,
                Gender.FEMALE,
                AgeRange.THIRTIES,
                "어디든 여행 가자. 지금 날씨 좋다.",
                UserTestData.defaultUser(),
                TravelMatePostStatus.CLOSED,
                LocalDateTime.now().minusDays(3L),
                LocalDateTime.now().minusDays(3L)
        );
    }
}
