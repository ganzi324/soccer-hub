package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.ganzi.soccerhub.auth.SessionUser;
import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.trip.application.command.AddTravelMateJoinRequestCommand;
import com.ganzi.soccerhub.trip.application.port.in.AddTravelMateJoinRequestUseCase;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping(path = "/v1/travel-mate-join-request")
@RequiredArgsConstructor
public class AddTravelMateJoinRequestController {

    private final AddTravelMateJoinRequestUseCase addTravelMateJoinRequestUseCase;

    @PostMapping
    ResponseEntity<ApiResponse<AddTravelMateJoinRequestResponse>> addTravelMateJoinRequest(
            @RequestBody AddTravelMateJoinRequestRequest request,
            @AuthenticationPrincipal SessionUser sessionUser) {
        AddTravelMateJoinRequestCommand command = new AddTravelMateJoinRequestCommand(
                TravelMatePost.PostId.of(request.getPostId()),
                User.UserId.of(sessionUser.id()),
                request.getMessage()
        );

        TravelMateJoinRequest travelMateJoinRequest = addTravelMateJoinRequestUseCase.add(command);
        return ResponseEntity.ok(ApiResponse.ok(AddTravelMateJoinRequestResponse.of(travelMateJoinRequest.getId().get())));
    }
}
