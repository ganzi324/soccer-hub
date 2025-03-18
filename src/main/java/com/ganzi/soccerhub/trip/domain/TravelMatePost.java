package com.ganzi.soccerhub.trip.domain;

import com.ganzi.soccerhub.place.domain.Place;
import com.ganzi.soccerhub.user.domain.Gender;
import com.ganzi.soccerhub.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class TravelMatePost {

    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private final PostId id;

    private final String title;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime startDate;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime endDate;

    private final List<Place> places;

    private final int capacity;

    private final Gender gender;

    private final AgeRange age;

    private final String description;

    private final User author;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime createdAt;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime updatedAt;

    public static TravelMatePost withoutId(String title, LocalDateTime startDate, LocalDateTime endDate, List<Place> places,
                                           int capacity, Gender gender, AgeRange age, String description, User author) {
        LocalDateTime now = LocalDateTime.now();
        return new TravelMatePost(null, title, startDate, endDate, List.copyOf(places), capacity, gender, age, description, author, now, now);
    }

    public static TravelMatePost withId(PostId id, String title, LocalDateTime startDate, LocalDateTime endDate, List<Place> places,
                                            int capacity, Gender gender, AgeRange age, String description, User author) {
        LocalDateTime now = LocalDateTime.now();
        return new TravelMatePost(id, title, startDate, endDate, List.copyOf(places), capacity, gender, age, description, author, now, now);
    }

    public Optional<PostId> getId() {
        return Optional.ofNullable(id);
    }

    public record PostId(Long value) {
        public static TravelMatePost.PostId of(Long id) {
            return new TravelMatePost.PostId(id);
        }
    }
}
