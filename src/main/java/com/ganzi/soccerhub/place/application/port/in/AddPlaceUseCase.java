package com.ganzi.soccerhub.place.application.port.in;

import com.ganzi.soccerhub.place.application.command.AddPlaceCommand;
import com.ganzi.soccerhub.place.domain.Place;

public interface AddPlaceUseCase {
    Place addPlace(AddPlaceCommand command);
}
