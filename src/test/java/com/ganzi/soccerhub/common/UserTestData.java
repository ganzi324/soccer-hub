package com.ganzi.soccerhub.common;

import com.ganzi.soccerhub.user.domain.User;

public class UserTestData {

    public static User defaultUser() {
        return User.withId(new User.UserId(1L), "ganzi", "1234", "ganzi324@gmail.com");
    }
}
