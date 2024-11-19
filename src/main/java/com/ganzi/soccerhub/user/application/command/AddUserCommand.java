package com.ganzi.soccerhub.user.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import com.ganzi.soccerhub.user.domain.UserType;
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

    @NotNull
    private final UserType userType;

    public AddUserCommand(String name, String email, String picture, UserType userType) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.userType = userType;

        this.validateSelf();
    }
}
