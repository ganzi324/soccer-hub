package com.ganzi.soccerhub.place.application.port.out;

import com.ganzi.soccerhub.place.domain.Place;

import java.util.List;

public interface LoadPlacePort {
    List<Place> loadPlaceByName(String name);
}
