package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.infra.hashid.HashId;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.trip.application.port.in.GetTravelMateJoinRequestQuery;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping(path = "/v1/travel-mate-join-request")
@RequiredArgsConstructor
public class GetTravelMateJoinRequestController {

    private final GetTravelMateJoinRequestQuery getTravelMateJoinRequestQuery;

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<TravelMateJoinRequestResponse>> getTravelMatePostById(@HashId("id") Long id) {
        TravelMateJoinRequest travelMateJoinRequest = getTravelMateJoinRequestQuery.getById(TravelMateJoinRequest.Id.of(id));
        return ResponseEntity.ok(ApiResponse.ok(TravelMateJoinRequestResponse.of(travelMateJoinRequest)));
    }
}
