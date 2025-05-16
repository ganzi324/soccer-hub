package com.ganzi.travelmate.place.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.travelmate.common.config.AbstractWebMvcTest;
import com.ganzi.travelmate.common.data.PlaceTestData;
import com.ganzi.travelmate.place.adaptor.in.web.AddPlaceController;
import com.ganzi.travelmate.place.application.command.AddPlaceCommand;
import com.ganzi.travelmate.place.application.port.in.AddPlaceUseCase;
import com.ganzi.travelmate.place.domain.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddPlaceController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddPlaceControllerTest extends AbstractWebMvcTest {

    @MockBean
    private AddPlaceUseCase addPlaceUseCase;

    @Test
    @WithMockUser
    void addPlace_thenOk() throws Exception {
        Place place = PlaceTestData.defaultPlace();

        AddPlaceCommand addPlaceCommand = new AddPlaceCommand(
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

        given(addPlaceUseCase.addPlace(any(AddPlaceCommand.class)))
                .willReturn(place);

        mvc.perform(post("/v1/places")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addPlaceCommand)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data").exists());
    }

}
