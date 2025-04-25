package com.ganzi.travelmate.trip.application.command;

import com.ganzi.travelmate.common.SelfValidating;
import com.ganzi.travelmate.trip.domain.AgeRange;
import com.ganzi.travelmate.trip.domain.TravelMatePostStatus;
import com.ganzi.travelmate.user.domain.Gender;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
public class TravelMatePostSearchCriteria extends SelfValidating<TravelMatePostSearchCriteria> {

    private final String title;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    @Getter(AccessLevel.NONE)
    private final List<Long> places;

    @Min(0)
    private final int capacity;

    private final Gender gender;

    private final AgeRange age;

    private final TravelMatePostStatus status;

    public TravelMatePostSearchCriteria(String title, LocalDateTime startDate, LocalDateTime endDate, List<Long> places, int capacity, Gender gender, AgeRange age, TravelMatePostStatus status) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.places = places;
        this.capacity = capacity;
        this.gender = gender;
        this.age = age;
        this.status = status;

        this.validateSelf();
    }

    public List<Long> getPlaces() {
        return places != null ? Collections.unmodifiableList(places) : List.of();
    }
}
