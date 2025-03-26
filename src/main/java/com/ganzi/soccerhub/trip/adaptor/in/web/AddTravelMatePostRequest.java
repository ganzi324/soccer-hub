package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ganzi.soccerhub.common.infra.json.IntegerListToLongListDeserializer;
import com.ganzi.soccerhub.trip.domain.AgeRange;
import com.ganzi.soccerhub.user.domain.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class AddTravelMatePostRequest {

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @JsonDeserialize(using = IntegerListToLongListDeserializer.class)
    private List<Long> places;

    private int capacity;

    private Gender gender;

    private AgeRange age;

    private String description;
    
}
