package com.ganzi.soccerhub.trip.domain;

import com.ganzi.soccerhub.place.domain.Place;
import com.ganzi.soccerhub.user.domain.Gender;

import java.time.LocalDateTime;
import java.util.List;

public record TravelMatePostUpdateVo(
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate,
        List<Place> places,
        int capacity,
        Gender gender,
        AgeRange age,
        String description
) {}
