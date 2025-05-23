package com.ganzi.travelmate.trip.adaptor.in.web;

import com.ganzi.travelmate.auth.SessionUser;
import com.ganzi.travelmate.common.WebAdaptor;
import com.ganzi.travelmate.common.infra.hashid.HashId;
import com.ganzi.travelmate.common.web.ApiResponse;
import com.ganzi.travelmate.trip.application.command.PatchTravelMatePostCommand;
import com.ganzi.travelmate.trip.application.port.in.PatchTravelMatePostUseCase;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping(path = "/v1/travel-mate-post")
@RequiredArgsConstructor
public class PatchTravelMatePostController {

    private final PatchTravelMatePostUseCase patchTravelMatePostUseCase;

    @PostMapping("/{id}")
    ResponseEntity<ApiResponse<PatchTravelMatePostResponse>> patchTravelMatePost(
            @RequestBody PatchTravelMatePostRequest request,
            @HashId Long id,
            @AuthenticationPrincipal SessionUser sessionUser
    ) {
        PatchTravelMatePostCommand command = new PatchTravelMatePostCommand(
                TravelMatePost.PostId.of(id),
                request.getTitle(),
                request.getStartDate(),
                request.getEndDate(),
                request.getPlaces(),
                request.getCapacity(),
                request.getGender(),
                request.getAge(),
                request.getDescription()
        );

        TravelMatePost travelMatePost = patchTravelMatePostUseCase.patch(command, User.UserId.of(sessionUser.id()));
        return ResponseEntity.ok(ApiResponse.ok(PatchTravelMatePostResponse.of(travelMatePost.getId().get())));
    }
}
