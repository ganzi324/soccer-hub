package com.ganzi.travelmate.trip.adaptor.in.web;

import com.ganzi.travelmate.auth.SessionUser;
import com.ganzi.travelmate.common.WebAdaptor;
import com.ganzi.travelmate.common.infra.hashid.HashId;
import com.ganzi.travelmate.common.web.ApiResponse;
import com.ganzi.travelmate.trip.application.port.in.GetTravelMateJoinRequestQuery;
import com.ganzi.travelmate.trip.domain.RequestStatus;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping(path = "/v1/travel-mate-join-request")
@RequiredArgsConstructor
public class GetTravelMateJoinRequestController {

    private final GetTravelMateJoinRequestQuery getTravelMateJoinRequestQuery;
    private final PagedResourcesAssembler<TravelMateJoinRequestResponse> pagedResourcesAssembler;

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<TravelMateJoinRequestResponse>> getTravelMatePostById(@HashId("id") Long id) {
        TravelMateJoinRequest travelMateJoinRequest = getTravelMateJoinRequestQuery.getById(TravelMateJoinRequest.Id.of(id));
        return ResponseEntity.ok(ApiResponse.ok(TravelMateJoinRequestResponse.of(travelMateJoinRequest)));
    }

    @GetMapping("/by-post/{postId}/join-requests")
    ResponseEntity<ApiResponse<PagedModel<EntityModel<TravelMateJoinRequestResponse>>>> getTravelMateJoinRequest(
            @HashId("postId") Long id,
            @RequestParam(required = false) RequestStatus status,
            @PageableDefault(size = 10) Pageable pageable,
            @AuthenticationPrincipal SessionUser user
    ) {
        Page<TravelMateJoinRequest> results = getTravelMateJoinRequestQuery.getByPost(TravelMatePost.PostId.of(id), status, User.UserId.of(user.id()), pageable);
        return ResponseEntity.ok(ApiResponse.ok(pagedResourcesAssembler.toModel(results.map(TravelMateJoinRequestResponse::of))));
    }
}
