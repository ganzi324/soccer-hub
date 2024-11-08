package com.ganzi.soccerhub.user.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddUserCommand extends SelfValidating<AddUserCommand> {

    @NotNull
    private final String name;

    @NotNull
    private final String password;

    @Email
    private final String email;

    public AddUserCommand(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;

        this.validateSelf();
    }
}
