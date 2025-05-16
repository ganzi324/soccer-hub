package com.ganzi.travelmate.place.application.command;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddPlaceCommandTest {

    @Test
    void construction_fail_whenNameEmpty() {
        assertThatThrownBy(() -> new AddPlaceCommand(
                "",
                "장소 설명",
                "도/시",
                "시/군/구",
                "도로명",
                "상세주소",
                "우편번호",
                0.0,
                0.0
        ))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("이름은 1~20 글자여야 합니다.");
    }

    @Test
    void construction_fail_whenNameNull() {
        assertThatThrownBy(() -> new AddPlaceCommand(
                null,
                "장소 설명",
                "도/시",
                "시/군/구",
                "도로명",
                "상세주소",
                "우편번호",
                0.0,
                0.0
        ))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("이름은 필수입니다.");
    }

    @Test
    void construction_fail_whenNameSizeOver() {
        assertThatThrownBy(() -> new AddPlaceCommand(
                "장소가20글자이상인이름은에러가발생합니다",
                "장소 설명",
                "도/시",
                "시/군/구",
                "도로명",
                "상세주소",
                "우편번호",
                0.0,
                0.0
        ))
                .isExactlyInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("이름은 1~20 글자여야 합니다.");
    }
}
