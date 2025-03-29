package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.trip.application.port.out.AddTravelMateJoinRequestPort;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TravelMateJoinRequestPersistenceAdaptor implements AddTravelMateJoinRequestPort {

    private final TravelMateJoinRequestRepository travelMateJoinRequestRepository;
    private final TravelMateJoinRequestMapper travelMateJoinRequestMapper;

    @Override
    public TravelMateJoinRequest add(TravelMateJoinRequest travelMateJoinRequest) {
        TravelMateJoinRequestJpaEntity travelMateJoinRequestJpaEntity = travelMateJoinRequestMapper.mapToJpaEntity(travelMateJoinRequest);
        TravelMateJoinRequestJpaEntity newTravelMateJoinRequestJpaEntity = travelMateJoinRequestRepository.save(travelMateJoinRequestJpaEntity);

        return travelMateJoinRequestMapper.mapToDomainEntity(newTravelMateJoinRequestJpaEntity);
    }
}
