package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.trip.domain.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelMateJoinRequestRepository extends JpaRepository<TravelMateJoinRequestJpaEntity, Long> {
    Page<TravelMateJoinRequestJpaEntity> findByTravelMatePostIdAndStatus(Long postId, RequestStatus requestStatus, Pageable pageable);
}
