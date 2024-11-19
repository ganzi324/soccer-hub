package com.ganzi.soccerhub.user.adaptor.out.web;

import com.ganzi.soccerhub.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class UserResponse {

    private String name;
    private String email;
    private String picture;

    public static UserResponse of(User user) {
        return new UserResponse(user.getName(), user.getEmail(), user.getPicture());
    }
}
