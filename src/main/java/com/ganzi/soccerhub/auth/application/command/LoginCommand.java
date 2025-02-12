package com.ganzi.soccerhub.auth.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import com.ganzi.soccerhub.common.annotation.Password;
import com.ganzi.soccerhub.user.application.command.AddUserCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginCommand extends SelfValidating<AddUserCommand> {

    @Email
    @NotNull
    private final String email;

    @Password
    @NotNull
    private final String password;

    public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;

        this.validateSelf();
    }
}
