package com.ganzi.travelmate.place.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<PlaceJpaEntity, Long> {
    List<PlaceJpaEntity> findByName(String name);
}
