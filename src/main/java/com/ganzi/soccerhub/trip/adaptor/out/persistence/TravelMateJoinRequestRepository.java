package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelMateJoinRequestRepository extends JpaRepository<TravelMateJoinRequestJpaEntity, Long> {
    Optional<TravelMateJoinRequestJpaEntity> findById(Long id);
}
