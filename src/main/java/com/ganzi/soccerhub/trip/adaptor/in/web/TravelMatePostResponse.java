package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.ganzi.soccerhub.trip.domain.AgeRange;
import com.ganzi.soccerhub.user.domain.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TravelMatePostResponse {

    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Set<String> places;

    private int capacity;

    private Gender gender;

    private AgeRange age;

    private String description;

    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
