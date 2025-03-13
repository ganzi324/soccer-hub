package com.ganzi.soccerhub.place.application.port.out;

import com.ganzi.soccerhub.place.domain.Place;

public interface AddPlacePort {
    Place save(Place place);
}
