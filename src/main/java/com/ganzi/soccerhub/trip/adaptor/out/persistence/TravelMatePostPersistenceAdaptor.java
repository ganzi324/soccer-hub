package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.trip.application.port.out.AddTravelMatePostPort;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TravelMatePostPersistenceAdaptor implements AddTravelMatePostPort, LoadTravelMatePostPort {

    private final TravelMatePostRepository travelMatePostRepository;
    private final TravelMatePostMapper travelMatePostMapper;

    @Override
    public TravelMatePost addPort(TravelMatePost travelMatePost) {
        TravelMatePostJpaEntity travelMatePostJpaEntity = travelMatePostMapper.mapToJpaEntity(travelMatePost);
        travelMatePostRepository.save(travelMatePostJpaEntity);

        return travelMatePostMapper.mapToDomainEntity(travelMatePostJpaEntity);
    }

    @Override
    public Optional<TravelMatePost> loadById(TravelMatePost.PostId id) {
        return travelMatePostRepository.findById(id.value()).map(travelMatePostMapper::mapToDomainEntity);
    }
}
