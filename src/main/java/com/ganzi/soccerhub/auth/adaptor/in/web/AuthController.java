package com.ganzi.soccerhub.auth.adaptor.in.web;

import com.ganzi.soccerhub.auth.application.command.LoginCommand;
import com.ganzi.soccerhub.auth.application.port.in.LoginUseCase;
import com.ganzi.soccerhub.auth.application.response.LoginResponse;
import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.common.web.ApiResponse;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.port.in.AddUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
class AuthController {

    private final LoginUseCase loginUseCase;
    private final AddUserUseCase addUserUseCase;

    @PostMapping(path = "/login")
    ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginCommand command = new LoginCommand(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(ApiResponse.ok(loginUseCase.execute(command)));
    }

    @PostMapping(path = "/sign-in")
    void signIn(@RequestBody SignInRequest requestDto) {
        AddUserCommand command = AddUserCommand.createNormalUser(
                requestDto.name(),
                requestDto.email(),
                requestDto.password()
        );

        addUserUseCase.addUser(command);
    }


}


