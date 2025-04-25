package com.ganzi.travelmate.place.adaptor.in.web;

import com.ganzi.travelmate.place.domain.Address;
import com.ganzi.travelmate.place.domain.Place;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceResponse {

    private Long id;
    private String name;
    private String description;
    private Address address;

    public static PlaceResponse of(Place place) {
        return new PlaceResponse(
                place.getId().get().value(),
                place.getName(),
                place.getDescription(),
                place.getAddress()
        );
    }
}
