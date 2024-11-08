package com.ganzi.soccerhub.user.application.command;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddUserCommandTest {

    @Test
    void constructionFailed() {
        assertThatThrownBy(() -> new AddUserCommand(null, "1234", "invalid email"))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("well-formed")
                .hasMessageContaining("null");
    }
}
