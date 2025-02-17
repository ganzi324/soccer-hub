package com.ganzi.soccerhub.team.application.command;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddTeamCommandTest {

    @Test
    void constructionFailed() {
        assertThatThrownBy(() -> new AddTeamCommand(null, "description", false, null))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("null");
    }
}
