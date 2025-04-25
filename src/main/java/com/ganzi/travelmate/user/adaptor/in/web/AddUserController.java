package com.ganzi.travelmate.user.adaptor.in.web;

import com.ganzi.travelmate.common.WebAdaptor;
import com.ganzi.travelmate.user.application.command.AddUserCommand;
import com.ganzi.travelmate.user.application.port.in.AddUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdaptor
@RestController("/v1/users")
@RequiredArgsConstructor
class AddUserController {

    private final AddUserUseCase addUserUseCase;

    @PostMapping
    void addUser(@RequestBody AddUserRequest requestDto) {
        AddUserCommand command = AddUserCommand.createNormalUser(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        addUserUseCase.addUser(command);
    }

}
