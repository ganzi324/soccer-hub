package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.ganzi.soccerhub.trip.domain.AgeRange;
import com.ganzi.soccerhub.user.domain.Gender;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record SearchTravelMatePostRequest(String title, LocalDateTime startDate, LocalDateTime endDate,
                                          List<Long> places, Integer capacity, Gender gender, AgeRange age) {
    public SearchTravelMatePostRequest(String title, LocalDateTime startDate, LocalDateTime endDate, List<Long> places, Integer capacity, Gender gender, AgeRange age) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.places = places != null ? places : Collections.emptyList();
        this.capacity = capacity != null ? capacity : 0;
        this.gender = gender != null ? gender : Gender.ANY;
        this.age = age != null ? age : AgeRange.ANY;
    }
}
