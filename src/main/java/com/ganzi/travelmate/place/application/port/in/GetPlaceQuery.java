package com.ganzi.travelmate.place.application.port.in;

import com.ganzi.travelmate.place.domain.Place;

import java.util.List;

public interface GetPlaceQuery {
    List<Place> getAllByIds(List<Place.PlaceId> ids);
}
