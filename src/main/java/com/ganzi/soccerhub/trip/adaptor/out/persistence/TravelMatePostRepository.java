package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.trip.domain.TravelMatePostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TravelMatePostRepository extends JpaRepository<TravelMatePostJpaEntity, Long> {
    @Modifying
    @Query("UPDATE TravelMatePostJpaEntity p SET p.status = :status WHERE p.id = :postId")
    int updateStatus(@Param("postId") Long postId, @Param("status") TravelMatePostStatus status);
}
