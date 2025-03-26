package com.ganzi.soccerhub.place.application.port.in;

import com.ganzi.soccerhub.place.domain.Place;

import java.util.List;

public interface GetPlaceQuery {
    List<Place> getAllByIds(List<Place.PlaceId> ids);
}
