package com.ganzi.soccerhub.place.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.place.application.port.in.GetPlaceQuery;
import com.ganzi.soccerhub.place.application.port.out.LoadPlacePort;
import com.ganzi.soccerhub.place.domain.Place;
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
