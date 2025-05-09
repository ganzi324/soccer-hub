package com.ganzi.travelmate.place.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class Place {

    @Getter(AccessLevel.NONE)
    private final Place.PlaceId id;

    private final String name;

    private final String description;

    private final Address address;

    public Optional<Place.PlaceId> getId() {
        return Optional.ofNullable(this.id);
    }

    public static Place withoutId(String name, String description, Address address) {
        return new Place(null, name, description, address);
    }

    public static Place withId(PlaceId id, String name, String description, Address address) {
        return new Place(id, name, description, address);
    }

    public record PlaceId(Long value) {
        public static Place.PlaceId of(Long id) {
            return new Place.PlaceId(id);
        }
    }
}
