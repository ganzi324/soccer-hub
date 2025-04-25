package com.ganzi.travelmate.trip.adaptor.in.web;

import com.ganzi.travelmate.common.WebAdaptor;
import com.ganzi.travelmate.common.infra.hashid.HashId;
import com.ganzi.travelmate.common.web.ApiResponse;
import com.ganzi.travelmate.trip.application.command.TravelMatePostSearchCriteria;
import com.ganzi.travelmate.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.travelmate.trip.application.port.in.GetTravelMatePostQuery;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
    private final PagedResourcesAssembler<TravelMatePostResponse> pagedResourcesAssembler;

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<TravelMatePostResponse>> getTravelMatePostById(@HashId("id") Long id) {
        TravelMatePost travelMatePost = getTravelMatePostQuery.getById(TravelMatePost.PostId.of(id)).orElseThrow(TravelMatePostNotFoundException::new);
        return ResponseEntity.ok(ApiResponse.ok(TravelMatePostResponse.of(travelMatePost)));
    }

    @GetMapping
    ResponseEntity<ApiResponse<PagedModel<EntityModel<TravelMatePostResponse>>>> searchTravelMate(
            SearchTravelMatePostRequest request,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        TravelMatePostSearchCriteria criteria = new TravelMatePostSearchCriteria(
                request.title(),
                request.startDate(),
                request.endDate(),
                request.places(),
                request.capacity(),
                request.gender(),
                request.age(),
                request.status()
        );

        Page<TravelMatePost> results = getTravelMatePostQuery.search(criteria, pageable);
        return ResponseEntity.ok(ApiResponse.ok(pagedResourcesAssembler.toModel(results.map(TravelMatePostResponse::of))));
    }

}
