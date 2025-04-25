package com.ganzi.travelmate.place.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.place.application.port.in.GetPlaceQuery;
import com.ganzi.travelmate.place.application.port.out.LoadPlacePort;
import com.ganzi.travelmate.place.domain.Place;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetPlaceService implements GetPlaceQuery {

    private final LoadPlacePort loadPlacePort;

    @Override
    public List<Place> getAllByIds(List<Place.PlaceId> ids) {
        return loadPlacePort.loadAllByIds(ids.stream().map(Place.PlaceId::value).toList());
    }
}
