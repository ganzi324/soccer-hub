package com.ganzi.soccerhub.user.adaptor.in.web;

import com.ganzi.soccerhub.auth.SessionUser;
import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.infra.hashid.HashId;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.user.application.exception.UserNotFoundException;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.application.response.UserResponse;
import com.ganzi.soccerhub.user.domain.User;
import com.ganzi.soccerhub.user.domain.User.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class GetUserController {

    private final GetUserQuery getUserQuery;

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> getUserById(@HashId("id") Long id) {
        User user = getUserQuery.getUserById(UserId.of(id)).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok(ApiResponse.ok(UserResponse.of(user)));
    }

    @GetMapping("/me")
    ResponseEntity<ApiResponse<UserResponse>> getMyProfile(@AuthenticationPrincipal SessionUser sessionUser) {
        User user = getUserQuery.getUserById(UserId.of(sessionUser.id())).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok(ApiResponse.ok(UserResponse.of(user)));
    }
}

