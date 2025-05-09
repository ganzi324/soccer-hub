package com.ganzi.travelmate.trip.application.command;

import com.ganzi.travelmate.common.data.PlaceTestData;
import com.ganzi.travelmate.trip.domain.AgeRange;
import com.ganzi.travelmate.user.domain.Gender;
import com.ganzi.travelmate.user.domain.User;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddTravelMatePostCommandTest {

    @Test
    void shouldFailWhenTitleLengthIsOutOfRange() {
        assertThatThrownBy(() -> new AddTravelMatePostCommand(
                "titl",
                    LocalDateTime.now().plusDays(1L),
                    LocalDateTime.now().plusDays(2L),
                    List.of(PlaceTestData.defaultPlace().getId().get().value()),
                    1,
                    Gender.MALE,
                    AgeRange.TWENTIES,
                    "description",
                    User.UserId.of(1L)
                ))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("제목의 길이");
    }

    @Test
    void shouldFailWhenStartDateEarlierThanCurrent() {
        assertThatThrownBy(() -> new AddTravelMatePostCommand(
                "title",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().plusDays(2L),
                List.of(PlaceTestData.defaultPlace().getId().get().value()),
                1,
                Gender.MALE,
                AgeRange.TWENTIES,
                "description",
                User.UserId.of(1L)
        ))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("시작 시간은");
    }

    @Test
    void shouldFailWhenEndDateEarlierThanStartDate() {
        assertThatThrownBy(() -> new AddTravelMatePostCommand(
                "title",
                LocalDateTime.now().plusDays(2L),
                LocalDateTime.now().plusDays(1L),
                List.of(PlaceTestData.defaultPlace().getId().get().value()),
                1,
                Gender.MALE,
                AgeRange.TWENTIES,
                "description",
                User.UserId.of(1L)
        ))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("종료 시간은 시작 시간과");
    }

    @Test
    void shouldFailWhenCapacityLessThenZero() {
        assertThatThrownBy(() -> new AddTravelMatePostCommand(
                "title",
                LocalDateTime.now().plusDays(1L),
                LocalDateTime.now().plusDays(2L),
                List.of(PlaceTestData.defaultPlace().getId().get().value()),
                -1,
                Gender.MALE,
                AgeRange.TWENTIES,
                "description",
                User.UserId.of(1L)
        ))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("0 이상");
    }
}

