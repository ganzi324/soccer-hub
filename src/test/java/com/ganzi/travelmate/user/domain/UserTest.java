package com.ganzi.travelmate.user.domain;

import com.ganzi.travelmate.common.UserTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("사용자 프로필 수정")
    void updateProfile() {
        User user = UserTestData.defaultUser();

        UserUpdateProfile userUpdateProfile = new UserUpdateProfile("New name", "update_picture.png");
        User updatedUser = user.update(userUpdateProfile);

        // then - 불변 객체로 반환
        assertThat(user).isNotEqualTo(updatedUser);

        // 동일한 UserId
        assertThat(updatedUser.getId()).isEqualTo(user.getId());

        // 수정된 필드
        assertThat(updatedUser.getName()).isNotEqualTo(user.getName());
        assertThat(updatedUser.getPicture()).isNotEqualTo(user.getPicture());
    }

}
