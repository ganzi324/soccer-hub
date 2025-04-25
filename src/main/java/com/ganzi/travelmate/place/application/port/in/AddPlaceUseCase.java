package com.ganzi.travelmate.place.application.port.in;

import com.ganzi.travelmate.place.application.command.AddPlaceCommand;
import com.ganzi.travelmate.place.domain.Place;

public interface AddPlaceUseCase {
    Place addPlace(AddPlaceCommand command);
}
