package com.ganzi.travelmate.common;

import com.ganzi.travelmate.user.domain.User;
import com.ganzi.travelmate.user.domain.UserRole;
import com.ganzi.travelmate.user.domain.UserType;

public class UserTestData {

    public static User defaultUser() {
        return User.withId(new User.UserId(1L), "ganzi324@gmail.com", "ganzi", "ganzi324@gmail.com", "qweR1234!", null, UserRole.USER, UserType.NORMAL);
    }

    public static User defaultSnsUser() {
        return User.withId(new User.UserId(2L), "userKey", "ganzi", "ganzi324@gmail.com", null, null, UserRole.USER, UserType.GOOGLE);
    }
}
