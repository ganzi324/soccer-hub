package com.ganzi.soccerhub.user.application.response;

import com.ganzi.soccerhub.common.infra.hashid.HashId;
import com.ganzi.soccerhub.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {

    @HashId
    private Long id;
    private String name;
    private String email;
    private String picture;

    public static UserResponse of(User user) {
        return new UserResponse(user.getId().get().value(), user.getName(), user.getEmail(), user.getPicture());
    }
}
