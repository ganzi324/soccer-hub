package com.ganzi.travelmate.trip.application.event;

import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;

public record TravelMateJoinRequestCreatedEvent(
        TravelMateJoinRequest travelMateJoinRequest
) {}
