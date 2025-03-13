package com.ganzi.soccerhub.place.application.service;

import com.ganzi.soccerhub.common.data.PlaceTestData;
import com.ganzi.soccerhub.place.application.command.AddPlaceCommand;
import com.ganzi.soccerhub.place.application.port.in.AddPlaceUseCase;
import com.ganzi.soccerhub.place.application.port.out.AddPlacePort;
import com.ganzi.soccerhub.place.application.port.out.LoadPlacePort;
import com.ganzi.soccerhub.place.domain.Place;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddPlaceServiceTest {

    private final LoadPlacePort loadPlacePort = Mockito.mock(LoadPlacePort.class);

    private final AddPlacePort addPlacePort = Mockito.mock(AddPlacePort.class);

    private AddPlaceUseCase addPlaceUseCase;

    @BeforeAll
    void setUp() {
        addPlaceUseCase = new AddPlaceService(loadPlacePort, addPlacePort);
    }

    @Test
    void addPlace() {
        Place place = PlaceTestData.defaultPlace();

        givenGetPlaceWillSucceed();
        givenAddPlaceWillSucceed(place);

        AddPlaceCommand command = new AddPlaceCommand(
                place.getName(),
                place.getDescription(),
                place.getAddress().getState(),
                place.getAddress().getCity(),
                place.getAddress().getStreet(),
                place.getAddress().getDetailAddress(),
                place.getAddress().getPostalCode(),
                place.getAddress().getLatitude(),
                place.getAddress().getLongitude()
        );
        Place savedPlace = addPlaceUseCase.addPlace(command);

        assertThat(savedPlace.getName()).isEqualTo(place.getName());
    }

    private void givenGetPlaceWillSucceed() {
        given(loadPlacePort.loadPlaceByName(anyString()))
                .willReturn(List.of());
    }

    private void givenAddPlaceWillSucceed(Place place) {
        given(addPlacePort.save(any(Place.class)))
                .willReturn(place);
    }
}
