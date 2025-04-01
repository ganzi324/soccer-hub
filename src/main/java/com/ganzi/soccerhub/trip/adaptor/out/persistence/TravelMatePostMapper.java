package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.place.adaptor.out.persistence.PlaceMapper;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.adaptor.out.persistence.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TravelMatePostMapper {

    private final PlaceMapper placeMapper;
    private final UserMapper userMapper;

    public TravelMatePost mapToDomainEntity(TravelMatePostJpaEntity travelMatePost) {
        return TravelMatePost.withId(
                new TravelMatePost.PostId(travelMatePost.getId()),
                travelMatePost.getTitle(),
                travelMatePost.getStartDate(),
                travelMatePost.getEndDate(),
                travelMatePost.getPlaces().stream().map(placeMapper::mapToDomainEntity).toList(),
                travelMatePost.getCapacity(),
                travelMatePost.getGender(),
                travelMatePost.getAge(),
                travelMatePost.getDescription(),
                userMapper.mapToDomainEntity(travelMatePost.getUser()),
                travelMatePost.getStatus(),
                travelMatePost.getCreatedAt(),
                travelMatePost.getUpdatedAt()
        );
    }

    public TravelMatePostJpaEntity mapToJpaEntity(TravelMatePost travelMatePost) {
        return new TravelMatePostJpaEntity(
                travelMatePost.getId().map(TravelMatePost.PostId::value).orElse(null),
                travelMatePost.getTitle(),
                travelMatePost.getStartDate(),
                travelMatePost.getEndDate(),
                travelMatePost.getPlaces().stream().map(placeMapper::mapToJpaEntity).toList(),
                travelMatePost.getCapacity(),
                travelMatePost.getGender(),
                travelMatePost.getAge(),
                travelMatePost.getDescription(),
                userMapper.mapToJpaEntity(travelMatePost.getAuthor()),
                travelMatePost.getStatus()
        );
    }
}
