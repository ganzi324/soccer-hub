package com.ganzi.soccerhub.user.adaptor.in.web;

import com.ganzi.soccerhub.auth.SessionUser;
import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.infra.hashid.HashId;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.user.application.command.PatchUserCommand;
import com.ganzi.soccerhub.user.application.port.in.PatchUserUseCase;
import com.ganzi.soccerhub.user.application.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class PatchUserController {

    private final PatchUserUseCase patchUserUseCase;

    @PostMapping("/{userId}")
    ResponseEntity<ApiResponse<UserResponse>> patchUser(
            @RequestBody PatchUserRequest requestDto,
            @HashId Long userId,
            @AuthenticationPrincipal SessionUser sessionUser) {
        PatchUserCommand command = new PatchUserCommand(userId, requestDto.getName(), requestDto.getPicture());
        return ResponseEntity.ok(ApiResponse.ok(UserResponse.of(patchUserUseCase.patch(command, sessionUser.id()))));
    }
}

