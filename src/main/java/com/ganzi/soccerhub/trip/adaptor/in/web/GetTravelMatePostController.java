package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.infra.hashid.HashId;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.soccerhub.trip.application.port.in.GetTravelMatePostQuery;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping(path = "/v1/travel-mate-post")
@RequiredArgsConstructor
public class GetTravelMatePostController {

    private final GetTravelMatePostQuery getTravelMatePostQuery;

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<TravelMatePostResponse>> getTravelMatePostById(@HashId("id") Long id) {
        TravelMatePost travelMatePost = getTravelMatePostQuery.getById(TravelMatePost.PostId.of(id)).orElseThrow(TravelMatePostNotFoundException::new);
        return ResponseEntity.ok(ApiResponse.ok(TravelMatePostResponse.of(travelMatePost)));
    }

}
