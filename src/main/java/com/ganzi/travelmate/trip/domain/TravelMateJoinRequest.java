package com.ganzi.travelmate.trip.domain;

import com.ganzi.travelmate.common.exception.InvalidStatusTransitionException;
import com.ganzi.travelmate.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class TravelMateJoinRequest {

    public static final int MESSAGE_MAX_LENGTH = 100;

    @Getter(AccessLevel.NONE)
    private final Id id;

    private final TravelMatePost travelMatePost;

    private final User requester;

    @EqualsAndHashCode.Exclude
    private final String message;

    private RequestStatus status;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime createdAt;

    public static TravelMateJoinRequest withId(Id id, TravelMatePost travelMatePost, User requester, String message, RequestStatus status, LocalDateTime createdAt) {
        return new TravelMateJoinRequest(id, travelMatePost, requester, message, status, createdAt);
    }

    public static TravelMateJoinRequest withoutId(TravelMatePost travelMatePost, User requester, String message) {
        return new TravelMateJoinRequest(null, travelMatePost, requester, message, RequestStatus.PENDING, LocalDateTime.now());
    }

    public Optional<Id> getId() {
        return Optional.ofNullable(id);
    }

    public void approve() {
        if (this.status != RequestStatus.PENDING) {
            throw new InvalidStatusTransitionException(this.status, RequestStatus.APPROVED);
        }
        this.status = RequestStatus.APPROVED;
    }

    public void reject() {
        if (this.status != RequestStatus.PENDING) {
            throw new InvalidStatusTransitionException(this.status, RequestStatus.REJECTED);
        }
        this.status = RequestStatus.REJECTED;
    }

    public record Id(Long value) {
        public static Id of(Long id) {
            return new TravelMateJoinRequest.Id(id);
        }
    }
}
