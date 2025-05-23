package com.ganzi.travelmate.trip.adaptor.in.web;

import com.ganzi.travelmate.auth.SessionUser;
import com.ganzi.travelmate.common.WebAdaptor;
import com.ganzi.travelmate.common.web.ApiResponse;
import com.ganzi.travelmate.trip.application.command.AddTravelMatePostCommand;
import com.ganzi.travelmate.trip.application.port.in.AddTravelMatePostUseCase;
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
class AddTravelMatePostController {

    private final AddTravelMatePostUseCase addTravelMatePostUseCase;

    @PostMapping
    ResponseEntity<ApiResponse<AddTravelMatePostResponse>> addTravelMatePost(@RequestBody AddTravelMatePostRequest request, @AuthenticationPrincipal SessionUser sessionUser) {
        AddTravelMatePostCommand command = new AddTravelMatePostCommand(
                request.getTitle(),
                request.getStartDate(),
                request.getEndDate(),
                request.getPlaces(),
                request.getCapacity(),
                request.getGender(),
                request.getAge(),
                request.getDescription(),
                User.UserId.of(sessionUser.id())
        );

        TravelMatePost travelMatePost = addTravelMatePostUseCase.addTravelMatePost(command);
        return ResponseEntity.ok(ApiResponse.ok(AddTravelMatePostResponse.of(travelMatePost.getId().get())));
    }

}
