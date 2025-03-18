package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.trip.application.port.out.AddTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TravelMatePostPersistenceAdaptor implements AddTravelMatePostPort {

    private final TravelMatePostRepository travelMatePostRepository;
    private final TravelMatePostMapper travelMatePostMapper;

    @Override
    public TravelMatePost addPort(TravelMatePost travelMatePost) {
        TravelMatePostJpaEntity travelMatePostJpaEntity = travelMatePostMapper.mapToJpaEntity(travelMatePost);
        travelMatePostRepository.save(travelMatePostJpaEntity);

        return travelMatePostMapper.mapToDomainEntity(travelMatePostJpaEntity);
    }
}
