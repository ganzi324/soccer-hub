package com.ganzi.soccerhub.common;

import com.ganzi.soccerhub.user.domain.User;
import com.ganzi.soccerhub.user.domain.UserRole;
import com.ganzi.soccerhub.user.domain.UserType;

public class UserTestData {

    public static User defaultUser() {
        return User.withId(new User.UserId(1L), "ganzi", "ganzi324@gmail.com", "1234", null, UserRole.USER, UserType.USER_GOOGLE);
    }
}
