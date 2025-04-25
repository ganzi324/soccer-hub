package com.ganzi.travelmate.place.application.port.out;

import com.ganzi.travelmate.place.domain.Place;

public interface AddPlacePort {
    Place save(Place place);
}
