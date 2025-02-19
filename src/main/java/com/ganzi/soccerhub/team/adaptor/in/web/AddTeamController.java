package com.ganzi.soccerhub.team.adaptor.in.web;

import com.ganzi.soccerhub.auth.SessionUser;
import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.team.application.command.AddTeamCommand;
import com.ganzi.soccerhub.team.application.port.in.AddTeamUseCase;
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
@RequestMapping("/api/v1/teams")
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
