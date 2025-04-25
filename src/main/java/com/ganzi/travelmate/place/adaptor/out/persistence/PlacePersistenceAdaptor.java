package com.ganzi.travelmate.place.adaptor.out.persistence;

import com.ganzi.travelmate.common.PersistenceAdapter;
import com.ganzi.travelmate.place.application.port.out.AddPlacePort;
import com.ganzi.travelmate.place.application.port.out.LoadPlacePort;
import com.ganzi.travelmate.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
class PlacePersistenceAdaptor implements AddPlacePort, LoadPlacePort {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Place> loadPlaceByName(String name) {
        List<PlaceJpaEntity> placeJpaEntities = placeRepository.findByName(name);

        return placeJpaEntities.stream()
                .map(placeMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Place> loadAllByIds(List<Long> ids) {
        List<PlaceJpaEntity> placeJpaEntities = placeRepository.findAllById(ids);

        return placeJpaEntities.stream()
                .map(placeMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Place save(Place place) {
        PlaceJpaEntity placeJpaEntity = placeMapper.mapToJpaEntity(place);
        placeRepository.save(placeJpaEntity);

        return placeMapper.mapToDomainEntity(placeJpaEntity);
    }
}
