package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.place.adaptor.out.persistence.PlaceMapper;
import com.ganzi.soccerhub.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.soccerhub.trip.application.port.out.AddTravelMatePostPort;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.soccerhub.trip.application.port.out.PatchTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TravelMatePostPersistenceAdaptor implements AddTravelMatePostPort, LoadTravelMatePostPort, PatchTravelMatePostPort {

    private final TravelMatePostRepository travelMatePostRepository;
    private final TravelMatePostMapper travelMatePostMapper;
    private final PlaceMapper placeMapper;

    @Override
    public TravelMatePost addPost(TravelMatePost travelMatePost) {
        TravelMatePostJpaEntity travelMatePostJpaEntity = travelMatePostMapper.mapToJpaEntity(travelMatePost);
        travelMatePostRepository.save(travelMatePostJpaEntity);

        return travelMatePostMapper.mapToDomainEntity(travelMatePostJpaEntity);
    }

    @Override
    public Optional<TravelMatePost> loadById(TravelMatePost.PostId id) {
        return travelMatePostRepository.findById(id.value()).map(travelMatePostMapper::mapToDomainEntity);
    }

    @Override
    public TravelMatePost patch(TravelMatePost travelMatePost) throws TravelMatePostNotFoundException {
        TravelMatePostJpaEntity travelMatePostJpaEntity = travelMatePostRepository.findById(travelMatePost.getId().get().value())
                .orElseThrow(TravelMatePostNotFoundException::new);
        travelMatePostJpaEntity.setTitle(travelMatePost.getTitle());
        travelMatePostJpaEntity.setStartDate(travelMatePost.getStartDate());
        travelMatePostJpaEntity.setEndDate(travelMatePost.getEndDate());
        travelMatePostJpaEntity.removePlaces();
        travelMatePost.getPlaces().forEach(place -> {
            travelMatePostJpaEntity.addPlace(placeMapper.mapToJpaEntity(place));
        });
        travelMatePostJpaEntity.setCapacity(travelMatePost.getCapacity());
        travelMatePostJpaEntity.setGender(travelMatePost.getGender());
        travelMatePostJpaEntity.setAge(travelMatePost.getAge());
        travelMatePostJpaEntity.setDescription(travelMatePost.getDescription());

        return travelMatePostMapper.mapToDomainEntity(travelMatePostJpaEntity);
    }
}
