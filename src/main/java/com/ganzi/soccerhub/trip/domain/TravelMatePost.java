package com.ganzi.soccerhub.trip.domain;

import com.ganzi.soccerhub.common.exception.InvalidStatusTransitionException;
import com.ganzi.soccerhub.place.domain.Place;
import com.ganzi.soccerhub.user.domain.Gender;
import com.ganzi.soccerhub.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class TravelMatePost {

    public static final int PLACE_MIN_SIZE = 1;
    public static final int PLACE_MAX_SIZE = 10;

    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private final PostId id;

    private final String title;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime startDate;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime endDate;

    @Getter(AccessLevel.NONE)
    private final List<Place> places;

    private final int capacity;

    private final Gender gender;

    private final AgeRange age;

    private final String description;

    private final User author;

    private TravelMatePostStatus status;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime createdAt;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime updatedAt;

    public static TravelMatePost withoutId(String title, LocalDateTime startDate, LocalDateTime endDate, List<Place> places,
                                           int capacity, Gender gender, AgeRange age, String description, User author) {
        LocalDateTime now = LocalDateTime.now();
        return new TravelMatePost(null, title, startDate, endDate, List.copyOf(places), capacity, gender, age, description, author, TravelMatePostStatus.OPEN, now, now);
    }

    public static TravelMatePost withId(PostId id, String title, LocalDateTime startDate, LocalDateTime endDate, List<Place> places,
                                            int capacity, Gender gender, AgeRange age, String description, User author, TravelMatePostStatus status) {
        LocalDateTime now = LocalDateTime.now();
        return new TravelMatePost(id, title, startDate, endDate, List.copyOf(places), capacity, gender, age, description, author, status, now, now);
    }

    public Optional<PostId> getId() {
        return Optional.ofNullable(id);
    }

    public List<Place> getPlaces() {
        return Collections.unmodifiableList(places);
    }

    public TravelMatePost update(TravelMatePostUpdateVo updateVo) {
        String title = Optional.ofNullable(updateVo.title()).orElse(this.title);
        LocalDateTime startDate = Optional.ofNullable(updateVo.startDate()).orElse(this.startDate);
        LocalDateTime endDate = Optional.ofNullable(updateVo.endDate()).orElse(this.endDate);
        List<Place> places = Optional.ofNullable(updateVo.places()).orElse(this.places);
        int capacity = updateVo.capacity();
        Gender gender = Optional.ofNullable(updateVo.gender()).orElse(this.gender);
        AgeRange ageRange = Optional.ofNullable(updateVo.age()).orElse(this.age);
        String description = Optional.ofNullable(updateVo.description()).orElse(this.description);

        return new TravelMatePost(this.id, title, startDate, endDate, places, capacity, gender, ageRange, description, this.author, this.status, this.createdAt, LocalDateTime.now());
    }

    public TravelMatePost changeStatus(TravelMatePostStatus newStatus) {
        if (!this.canChangeTo(newStatus)) {
            throw new InvalidStatusTransitionException(this.status, newStatus);
        }
        return new TravelMatePost(this.id, this.title, this.getStartDate(), this.getEndDate(), this.getPlaces(), this.capacity, this.gender, this.age, this.description, this.author, newStatus, this.createdAt, this.updatedAt);
    }

    private boolean canChangeTo(TravelMatePostStatus newStatus) {
        if (newStatus == TravelMatePostStatus.CLOSED) {
            return !this.status.equals(TravelMatePostStatus.DRAFT);
        } else if (newStatus == TravelMatePostStatus.PENDING) {
            return this.status.equals(TravelMatePostStatus.OPEN);
        }
        return false;
    }

    public record PostId(Long value) {
        public static TravelMatePost.PostId of(Long id) {
            return new TravelMatePost.PostId(id);
        }
    }
}
