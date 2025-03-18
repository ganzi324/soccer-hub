package com.ganzi.soccerhub.trip.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import com.ganzi.soccerhub.trip.domain.AgeRange;
import com.ganzi.soccerhub.user.domain.Gender;
import com.ganzi.soccerhub.user.domain.User;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AddTravelMatePostCommand extends SelfValidating<AddTravelMatePostCommand> {

    @Size(min = 5, max = 50, message = "제목의 길이는 5~50 이어야 합니다.")
    @NotBlank
    private final String title;

    @Future(message = "시작 시간은 현재 시간보다 이후여야 합니다.")
    @NotNull
    private final LocalDateTime startDate;

    @Future(message = "종료 시간은 현재 시간보다 이후여야 합니다.")
    @NotNull
    private final LocalDateTime endDate;

    private final List<Long> places;

    @Min(value = 0, message = "값은 0 이상이어야 합니다.")
    private final int capacity;

    private final Gender gender;

    private final AgeRange age;

    private final String description;

    private final User.UserId authorId;

    public AddTravelMatePostCommand(String title, LocalDateTime startDate, LocalDateTime endDate, List<Long> places, int capacity, Gender gender, AgeRange age, String description, User.UserId authorId) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.places = places;
        this.capacity = capacity;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.authorId = authorId;

        this.validateSelf();
    }

    @AssertTrue(message = "종료 시간은 시작 시간과 같거나 이후여야 합니다.")
    public boolean isValidDateRange() {
        return startDate == null || endDate == null || !endDate.isBefore(startDate);
    }
}
