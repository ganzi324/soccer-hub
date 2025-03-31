package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.common.exception.DomainIdNotFoundException;
import com.ganzi.soccerhub.trip.application.port.in.GetTravelMateJoinRequestQuery;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMateJoinRequestPort;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetTravelMateJoinRequestService implements GetTravelMateJoinRequestQuery {

    private final LoadTravelMateJoinRequestPort loadTravelMateJoinRequestPort;

    @Override
    public TravelMateJoinRequest getById(TravelMateJoinRequest.Id id) {
        return loadTravelMateJoinRequestPort.loadById(id)
                .orElseThrow(() -> new DomainIdNotFoundException(TravelMateJoinRequest.class));
    }
}
