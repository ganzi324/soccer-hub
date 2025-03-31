package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.trip.domain.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelMateJoinRequestRepository extends JpaRepository<TravelMateJoinRequestJpaEntity, Long> {
    List<TravelMateJoinRequestJpaEntity> findByTravelMatePostIdAndStatus(Long postId, RequestStatus requestStatus);
}
