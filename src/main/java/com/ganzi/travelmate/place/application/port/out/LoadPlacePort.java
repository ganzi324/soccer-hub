package com.ganzi.travelmate.place.application.port.out;

import com.ganzi.travelmate.place.domain.Place;

import java.util.List;

public interface LoadPlacePort {
    List<Place> loadPlaceByName(String name);

    List<Place> loadAllByIds(List<Long> ids);
}
