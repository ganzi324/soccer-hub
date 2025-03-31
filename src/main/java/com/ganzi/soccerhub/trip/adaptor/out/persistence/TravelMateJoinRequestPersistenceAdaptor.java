package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.trip.application.port.out.AddTravelMateJoinRequestPort;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMateJoinRequestPort;
import com.ganzi.soccerhub.trip.domain.RequestStatus;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TravelMateJoinRequestPersistenceAdaptor implements
        AddTravelMateJoinRequestPort,LoadTravelMateJoinRequestPort {

    private final TravelMateJoinRequestRepository travelMateJoinRequestRepository;
    private final TravelMateJoinRequestMapper travelMateJoinRequestMapper;

    @Override
    public TravelMateJoinRequest add(TravelMateJoinRequest travelMateJoinRequest) {
        TravelMateJoinRequestJpaEntity travelMateJoinRequestJpaEntity = travelMateJoinRequestMapper.mapToJpaEntity(travelMateJoinRequest);
        TravelMateJoinRequestJpaEntity newTravelMateJoinRequestJpaEntity = travelMateJoinRequestRepository.save(travelMateJoinRequestJpaEntity);

        return travelMateJoinRequestMapper.mapToDomainEntity(newTravelMateJoinRequestJpaEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TravelMateJoinRequest> loadById(TravelMateJoinRequest.Id id) {
        return travelMateJoinRequestRepository.findById(id.value()).map(travelMateJoinRequestMapper::mapToDomainEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelMateJoinRequest> loadByPost(TravelMatePost.PostId postId, RequestStatus status) {
        return travelMateJoinRequestRepository.findByTravelMatePostIdAndStatus(postId.value(), status)
                .stream()
                .map(travelMateJoinRequestMapper::mapToDomainEntity)
                .toList();
    }
}
