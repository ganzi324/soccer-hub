package com.ganzi.soccerhub.auth.adaptor.in.web;

import com.ganzi.soccerhub.auth.application.port.in.RenewAccessTokenUseCase;
import com.ganzi.soccerhub.auth.application.result.AuthenticationTokens;
import com.ganzi.soccerhub.auth.exception.BearerTokenNotFoundException;
import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.port.in.AddUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdaptor
@RestController
@RequestMapping(path = "/v1/auth")
@RequiredArgsConstructor
class AuthController {

    private final AddUserUseCase addUserUseCase;
    private final RenewAccessTokenUseCase renewAccessTokenUseCase;

    @PostMapping(path = "/sign-in")
    void signIn(@RequestBody SignInRequest requestDto) {
        AddUserCommand command = AddUserCommand.createNormalUser(
                requestDto.name(),
                requestDto.email(),
                requestDto.password()
        );

        addUserUseCase.addUser(command);
    }

    @PostMapping("/refresh")
    ResponseEntity<ApiResponse<AuthenticationTokens>> refreshToken(@RequestHeader HttpHeaders httpHeaders) {
        String authorizationHeader = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new BearerTokenNotFoundException();
        }

        String refreshToken = authorizationHeader.substring(7);
        return ResponseEntity.ok(ApiResponse.ok(renewAccessTokenUseCase.execute(refreshToken)));
    }

}


