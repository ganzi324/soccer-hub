package com.ganzi.travelmate.trip.application.command;

import com.ganzi.travelmate.common.SelfValidating;
import com.ganzi.travelmate.trip.domain.AgeRange;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PatchTravelMatePostCommand extends SelfValidating<PatchTravelMatePostCommand> {

    @NotNull
    private final TravelMatePost.PostId id;

    @Size(min = 5, max = 50, message = "제목의 길이는 5~50 이어야 합니다.")
    @NotBlank
    private final String title;

    @Future(message = "시작 시간은 현재 시간보다 이후여야 합니다.")
    @NotNull
    private final LocalDateTime startDate;

    @Future(message = "종료 시간은 현재 시간보다 이후여야 합니다.")
    @NotNull
    private final LocalDateTime endDate;

    @Size(min = TravelMatePost.PLACE_MIN_SIZE, max = TravelMatePost.PLACE_MAX_SIZE, message = "장소를 최소 1개, 최대 10개 까지 선택해야 합니다.")
    private final List<Long> places;

    @Min(value = 0, message = "값은 0 이상이어야 합니다.")
    private final int capacity;

    private final Gender gender;

    private final AgeRange age;

    private final String description;

    public PatchTravelMatePostCommand(TravelMatePost.PostId id, String title, LocalDateTime startDate, LocalDateTime endDate, List<Long> places, int capacity, Gender gender, AgeRange age, String description) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.places = places;
        this.capacity = capacity;
        this.gender = gender;
        this.age = age;
        this.description = description;

        this.validateSelf();
    }
}
