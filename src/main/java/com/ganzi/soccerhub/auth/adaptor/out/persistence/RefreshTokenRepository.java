package com.ganzi.soccerhub.auth.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface RefreshTokenRepository extends JpaRepository<RefreshTokenJpaEntity, Long> {
}
