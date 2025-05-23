package com.ganzi.travelmate.user.application.command;

import com.ganzi.travelmate.common.SelfValidating;
import com.ganzi.travelmate.user.domain.UserUpdateProfile;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PatchUserCommand extends SelfValidating<AddUserCommand> {

    @NotNull
    private final Long id;

    private final String name;

    private final String picture;

    public PatchUserCommand(Long id, String name, String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;

        this.validateSelf();
    }

    public UserUpdateProfile toUpdateVo() {
        return new UserUpdateProfile(name, picture);
    }
}
