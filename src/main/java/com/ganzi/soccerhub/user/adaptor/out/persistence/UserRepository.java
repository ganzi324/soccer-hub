package com.ganzi.soccerhub.user.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findByUserKey(String userKey);
}
