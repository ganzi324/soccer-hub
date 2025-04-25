package com.ganzi.travelmate.place.adaptor.out.persistence;

import com.ganzi.travelmate.place.domain.Address;
import com.ganzi.travelmate.place.domain.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {

    public Place mapToDomainEntity(PlaceJpaEntity place) {
        AddressVO addressVO = place.getAddress();

        return Place.withId(
                new Place.PlaceId(place.getId()),
                place.getName(),
                place.getDescription(),
                new Address(
                        addressVO.getState(),
                        addressVO.getCity(),
                        addressVO.getStreet(),
                        addressVO.getDetailAddress(),
                        addressVO.getPostalCode(),
                        addressVO.getLatitude(),
                        addressVO.getLongitude()
                )
        );
    }

    public PlaceJpaEntity mapToJpaEntity(Place place) {
        Address address = place.getAddress();

        return new PlaceJpaEntity(
                place.getId().map(Place.PlaceId::value).orElse(null),
                place.getName(),
                place.getDescription(),
                new AddressVO(
                        address.getState(),
                        address.getCity(),
                        address.getStreet(),
                        address.getDetailAddress(),
                        address.getPostalCode(),
                        address.getLatitude(),
                        address.getLongitude()
                )
        );
    }

}
