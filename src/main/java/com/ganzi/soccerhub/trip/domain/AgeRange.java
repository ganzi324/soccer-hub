package com.ganzi.soccerhub.trip.domain;

import com.ganzi.soccerhub.common.convertor.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AgeRange implements CommonType {

    TEENAGER("TEENAGER", 10, 19, "10대"),
    TWENTIES("TWENTIES", 20, 29, "20대"),
    THIRTIES("THIRTIES", 30, 39, "30대"),
    FORTIES("FORTIES", 40, 49, "40대"),
    FIFTIES("FIFTIES", 50, 59, "50대"),
    SIXTIES("SIXTIES", 60, 69, "60대"),
    SEVENTIES("SEVENTIES", 70, 79, "70대"),
    EIGHTIES("EIGHTIES", 80, 89, "80대"),
    ANY("ANY", 0, 100, "제한 없음");

    private final String code;
    private final int minAge;
    private final int maxAge;
    private final String description;

    public static AgeRange fromAge(int age) {
        for (AgeRange range : values()) {
            if (age >= range.getMinAge() && age <= range.getMaxAge()) {
                return range;
            }
        }
        return ANY;
    }

    public static AgeRange fromCode(String code) {
        for (AgeRange range : values()) {
            if (range.getCode().equals(code)) {
                return range;
            }
        }
        throw new IllegalArgumentException("Invalid age range code: " + code);
    }
}
