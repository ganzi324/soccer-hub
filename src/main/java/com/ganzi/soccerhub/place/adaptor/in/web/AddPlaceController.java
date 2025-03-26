package com.ganzi.soccerhub.place.adaptor.in.web;

import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.place.application.command.AddPlaceCommand;
import com.ganzi.soccerhub.place.application.port.in.AddPlaceUseCase;
import com.ganzi.soccerhub.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping("/v1/places")
@RequiredArgsConstructor
public class AddPlaceController {

    private final AddPlaceUseCase addPlaceUseCase;

    @PostMapping
    ResponseEntity<ApiResponse<PlaceResponse>> addPlace(@RequestBody AddPlaceCommand request) {
        Place place = addPlaceUseCase.addPlace(request);
        return ResponseEntity.ok(ApiResponse.ok(PlaceResponse.of(place)));
    }

}
