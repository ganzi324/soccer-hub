package com.ganzi.travelmate.trip.application.command;

import com.ganzi.travelmate.common.SelfValidating;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddTravelMateJoinRequestCommand extends SelfValidating<AddTravelMateJoinRequestCommand> {

    @NotNull
    private final TravelMatePost.PostId travelMatePostId;

    @NotNull
    private final User.UserId requesterId;

    @Size(max = TravelMateJoinRequest.MESSAGE_MAX_LENGTH, message = "메시지 최대 길이는 100자 입니다.")
    private final String message;

    public AddTravelMateJoinRequestCommand(TravelMatePost.PostId travelMatePostId, User.UserId requesterId, String message) {
        this.travelMatePostId = travelMatePostId;
        this.requesterId = requesterId;
        this.message = message;

        this.validateSelf();
    }
}
