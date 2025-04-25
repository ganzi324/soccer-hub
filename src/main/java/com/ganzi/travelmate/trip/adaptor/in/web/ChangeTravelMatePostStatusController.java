package com.ganzi.travelmate.trip.adaptor.in.web;

import com.ganzi.travelmate.auth.SessionUser;
import com.ganzi.travelmate.common.WebAdaptor;
import com.ganzi.travelmate.common.infra.hashid.HashId;
import com.ganzi.travelmate.common.web.ApiResponse;
import com.ganzi.travelmate.trip.application.port.in.ChangeTravelMatePostStatusUseCase;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@WebAdaptor
@RestController
@RequestMapping(path = "/v1/travel-mate-post")
@RequiredArgsConstructor
public class ChangeTravelMatePostStatusController {

    private final ChangeTravelMatePostStatusUseCase changeTravelMatePostStatusUseCase;

    @PatchMapping("/{postId}/status")
    public ResponseEntity<ApiResponse<Void>> changeStatus(
            @HashId("postId") Long postId,
            @RequestBody TravelMatePostChangeStatusRequest request,
            @AuthenticationPrincipal SessionUser user) {

        changeTravelMatePostStatusUseCase.changeStatus(
                request.status(),
                TravelMatePost.PostId.of(postId),
                User.UserId.of(user.id()));

        return ResponseEntity.ok(ApiResponse.ok());
    }
}
