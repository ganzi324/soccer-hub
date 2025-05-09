package com.ganzi.travelmate.trip.adaptor.in.web;

import com.ganzi.travelmate.common.infra.hashid.HashId;
import com.ganzi.travelmate.place.domain.Place;
import com.ganzi.travelmate.trip.domain.AgeRange;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.trip.domain.TravelMatePostStatus;
import com.ganzi.travelmate.user.domain.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TravelMatePostResponse {

    @HashId("id")
    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<String> places;

    private int capacity;

    private Gender gender;

    private AgeRange age;

    private String description;

    private String author;

    private TravelMatePostStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static TravelMatePostResponse of(TravelMatePost travelMatePost) {
        return new TravelMatePostResponse(
                travelMatePost.getId().get().value(),
                travelMatePost.getTitle(),
                travelMatePost.getStartDate(),
                travelMatePost.getEndDate(),
                travelMatePost.getPlaces().stream().map(Place::getName).toList(),
                travelMatePost.getCapacity(),
                travelMatePost.getGender(),
                travelMatePost.getAge(),
                travelMatePost.getDescription(),
                travelMatePost.getAuthor().getName(),
                travelMatePost.getStatus(),
                travelMatePost.getCreatedAt(),
                travelMatePost.getUpdatedAt()
        );
    }
}
