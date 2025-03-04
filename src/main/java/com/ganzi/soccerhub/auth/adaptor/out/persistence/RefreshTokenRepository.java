package com.ganzi.soccerhub.auth.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface RefreshTokenRepository extends JpaRepository<RefreshTokenJpaEntity, Long> {
    Optional<RefreshTokenJpaEntity> findByUserIdAndToken(Long userId, String token);
}
