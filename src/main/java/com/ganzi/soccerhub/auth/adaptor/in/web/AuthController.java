package com.ganzi.soccerhub.auth.adaptor.in.web;

import com.ganzi.soccerhub.common.WebAdaptor;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import com.ganzi.soccerhub.user.application.port.in.AddUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
class AuthController {

    private final AddUserUseCase addUserUseCase;

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


