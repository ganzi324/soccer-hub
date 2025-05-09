package com.ganzi.travelmate.team.adaptor.in.web;

import com.ganzi.travelmate.auth.SessionUser;
import com.ganzi.travelmate.common.WebAdaptor;
import com.ganzi.travelmate.common.web.ApiResponse;
import com.ganzi.travelmate.team.application.command.AddTeamCommand;
import com.ganzi.travelmate.team.application.port.in.AddTeamUseCase;
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
@RequestMapping("/v1/teams")
@RequiredArgsConstructor
class AddTeamController {

    private final AddTeamUseCase addTeamUseCase;

    @PostMapping
    ResponseEntity<ApiResponse<?>> addTeam(
            @RequestBody AddTeamRequest requestDto,
            @AuthenticationPrincipal SessionUser sessionUser) {
        AddTeamCommand command = new AddTeamCommand(
          requestDto.getName(),
          requestDto.isPrivate(),
          requestDto.getDescription(),
          new User.UserId(sessionUser.id())
        );

        return ResponseEntity.ok(ApiResponse.ok(AddTeamResponse.of(addTeamUseCase.addTeam(command).value())));
    }
}
