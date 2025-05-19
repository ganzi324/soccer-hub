package com.ganzi.travelmate.place.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.travelmate.common.config.AbstractWebMvcTest;
import com.ganzi.travelmate.common.data.PlaceTestData;
import com.ganzi.travelmate.common.docs.RestDocsCommonFields;
import com.ganzi.travelmate.place.adaptor.in.web.AddPlaceController;
import com.ganzi.travelmate.place.application.command.AddPlaceCommand;
import com.ganzi.travelmate.place.application.port.in.AddPlaceUseCase;
import com.ganzi.travelmate.place.domain.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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

        mvc.perform(
                post("/v1/places")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addPlaceCommand)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andDo(document("places/add-place",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("장소 이름 (1~20자)"),
                                fieldWithPath("description").type(JsonFieldType.STRING).optional().description("장소 주소"),
                                fieldWithPath("state").type(JsonFieldType.STRING).description("주/도 (1~20자"),
                                fieldWithPath("city").type(JsonFieldType.STRING).description("시/군/구(1~50자)"),
                                fieldWithPath("street").type(JsonFieldType.STRING).description("도로명 (1~100자)"),
                                fieldWithPath("detailAddress").type(JsonFieldType.STRING).optional().description("상세 주소"),
                                fieldWithPath("postalCode").type(JsonFieldType.STRING).description("우편번호 (1~10자)"),
                                fieldWithPath("latitude").type(JsonFieldType.NUMBER).optional().description("위도"),
                                fieldWithPath("longitude").type(JsonFieldType.NUMBER).optional().description("경도")
                        ),
                        responseFields(
                                RestDocsCommonFields.commonResponseFields()
                        ).andWithPrefix("data.",
                                fieldWithPath("id").description("장소 ID"),
                                fieldWithPath("name").description("장소 이름"),
                                fieldWithPath("description").description("장소 설명"),
                                fieldWithPath("address.state").description("주/도"),
                                fieldWithPath("address.city").description("시/군/구"),
                                fieldWithPath("address.street").description("도로명"),
                                fieldWithPath("address.detailAddress").description("상세 주소"),
                                fieldWithPath("address.postalCode").description("우편번호"),
                                fieldWithPath("address.latitude").description("위도"),
                                fieldWithPath("address.longitude").description("경도"))
                ));
    }

}
