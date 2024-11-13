package com.ganzi.soccerhub.user.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddUserCommand extends SelfValidating<AddUserCommand> {

    @NotNull
    private final String name;

    @Email
    private final String email;

    @NotNull
    private final String picture;

    public AddUserCommand(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;

        this.validateSelf();
    }
}
