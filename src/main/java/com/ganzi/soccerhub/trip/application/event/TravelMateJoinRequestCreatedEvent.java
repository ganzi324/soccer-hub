package com.ganzi.soccerhub.trip.application.event;

import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;

public record TravelMateJoinRequestCreatedEvent(
        TravelMateJoinRequest travelMateJoinRequest
) {}
