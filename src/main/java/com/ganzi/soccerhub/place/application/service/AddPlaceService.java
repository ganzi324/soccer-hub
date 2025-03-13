package com.ganzi.soccerhub.place.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.place.application.command.AddPlaceCommand;
import com.ganzi.soccerhub.place.application.exception.DuplicatePlaceException;
import com.ganzi.soccerhub.place.application.port.in.AddPlaceUseCase;
import com.ganzi.soccerhub.place.application.port.out.AddPlacePort;
import com.ganzi.soccerhub.place.application.port.out.LoadPlacePort;
import com.ganzi.soccerhub.place.domain.Address;
import com.ganzi.soccerhub.place.domain.Place;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class AddPlaceService implements AddPlaceUseCase {

    private final LoadPlacePort loadPlacePort;
    private final AddPlacePort addPlacePort;

    @Override
    public Place addPlace(AddPlaceCommand command) {
        checkDuplication(command.getName(), command.getAddress());

        Place place = Place.withoutId(command.getName(), command.getDescription(), command.getAddress());

        return addPlacePort.save(place);
    }

    private void checkDuplication(String name, Address address) {
        List<Place> places = loadPlacePort.loadPlaceByName(name);

        if (!places.isEmpty()) {
            if (places.stream().anyMatch(place -> place.getAddress().equals(address))) {
                log.info("There is a same place. (name : {}, address : {})", name, address.toString());
                throw new DuplicatePlaceException();
            }
        }
    }
}
