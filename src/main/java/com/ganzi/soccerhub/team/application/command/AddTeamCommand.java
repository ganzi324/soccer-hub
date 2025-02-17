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

    private final String description;

    @NotNull
    private final boolean isPrivate;

    @NotNull
    private final User.UserId createdBy;

    public AddTeamCommand(String name, String description, boolean isPrivate, User.UserId createdBy) {
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.createdBy = createdBy;

        this.validateSelf();
    }
}
