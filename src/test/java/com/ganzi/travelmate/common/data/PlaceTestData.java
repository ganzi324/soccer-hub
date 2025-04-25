package com.ganzi.travelmate.common.data;

import com.ganzi.travelmate.place.domain.Address;
import com.ganzi.travelmate.place.domain.Place;

public class PlaceTestData {

    public static Place defaultPlace() {
        return Place.withId(
                new Place.PlaceId(1L),
                "남산전망대",
                "서울 남산에 위치해 있음",
                new Address("서울", "용산구", "남산공원길 105", "", "04340", 0, 0)
        );
    }

    public static Place sample1() {
        return Place.withId(
                new Place.PlaceId(2L),
                "여의도 공원",
                "4월 여의도의 벚꽃은 아릅답습니다.",
                new Address("서울", "영등포구", "'여의동로 330", "한강사업본부 여의도안내센터", "07337", 37.526872, 126.934754)
        );
    }

    public static Place sample2() {
        return Place.withId(
                new Place.PlaceId(3L),
                "경복궁",
                "조선 왕조의 궁궐로, 역사적인 명소입니다.",
                new Address("서울", "종로구", "경복궁로 161", "경복궁", "03135", 37.578948, 126.976852)
        );
    }

    public static Place sample3() {
        return Place.withId(
                new Place.PlaceId(4L),
                "남산서울타워",
                "서울의 전경을 한눈에 볼 수 있는 명소입니다.",
                new Address("서울", "중구", "남산공원길 105", "남산타워", "04340", 37.551169, 126.988227)
        );
    }
}