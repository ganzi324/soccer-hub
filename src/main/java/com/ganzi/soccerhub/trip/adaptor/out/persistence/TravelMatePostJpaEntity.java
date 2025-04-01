package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.infra.persistence.BaseTimeEntity;
import com.ganzi.soccerhub.place.adaptor.out.persistence.PlaceJpaEntity;
import com.ganzi.soccerhub.trip.domain.AgeRange;
import com.ganzi.soccerhub.trip.domain.TravelMatePostStatus;
import com.ganzi.soccerhub.user.adaptor.out.persistence.UserJpaEntity;
import com.ganzi.soccerhub.user.domain.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "travel_mate_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelMatePostJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany
    @JoinTable(
            name = "travel_mate_post_place",
            joinColumns = @JoinColumn(name = "travel_mate_post_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    @Getter(AccessLevel.NONE)
    private List<PlaceJpaEntity> places = new ArrayList<>();

    private int capacity;

    @Column(nullable = false)
    @Convert(converter = GenderConvertor.class)
    private Gender gender;

    @Column(nullable = false)
    @Convert(converter = AgeRangeConvertor.class)
    private AgeRange age;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @Column(nullable = false)
    @Convert(converter = TravelMatePostStatusConvertor.class)
    private TravelMatePostStatus status;

    public List<PlaceJpaEntity> getPlaces() {
        return Collections.unmodifiableList(places);
    }

    public void removePlaces() {
        places.clear();
    }

    public void addPlace(PlaceJpaEntity place) {
        places.add(place);
    }
}
