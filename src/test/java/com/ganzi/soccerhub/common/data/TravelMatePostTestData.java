package com.ganzi.soccerhub.common.data;

import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.trip.domain.AgeRange;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
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
                UserTestData.defaultUser()
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
                UserTestData.defaultUser()
        );
    }
}
