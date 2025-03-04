package com.ganzi.soccerhub.team.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import com.ganzi.soccerhub.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddTeamCommand extends SelfValidating<AddTeamCommand> {

    @NotBlank
    private final String name;

    @NotNull
    private final boolean isPrivate;

    private final String description;

    @NotNull
    private final User.UserId createdBy;

    public AddTeamCommand(String name, boolean isPrivate, String description, User.UserId createdBy) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.description = description;
        this.createdBy = createdBy;

        this.validateSelf();
    }
}
