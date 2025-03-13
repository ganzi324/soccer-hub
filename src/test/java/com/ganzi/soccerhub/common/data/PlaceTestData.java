package com.ganzi.soccerhub.common.data;

import com.ganzi.soccerhub.place.domain.Address;
import com.ganzi.soccerhub.place.domain.Place;

public class PlaceTestData {

    public static Place defaultPlace() {
        return Place.withId(
                new Place.PlaceId(1L),
                "남산전망대",
                "서울 남산에 위치해 있음",
                new Address("서울", "용산구", "남산공원길 105", "", "04340", 0, 0)
        );
    }
}
