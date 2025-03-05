package com.ganzi.soccerhub.user.application.command;

import com.ganzi.soccerhub.user.domain.UserType;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddUserCommandTest {

    @Test
    void constructionFailed() {
        assertThatThrownBy(() -> AddUserCommand.createSnsUser("normal_email", null, "invalid email", "some_picture.jpg", UserType.GOOGLE))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("well-formed")
                .hasMessageContaining("null");
    }
}
