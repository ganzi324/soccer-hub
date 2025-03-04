package com.ganzi.soccerhub.auth.adaptor.out.persistence;

import com.ganzi.soccerhub.auth.domain.RefreshToken;
import com.ganzi.soccerhub.user.adaptor.out.persistence.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class RefreshTokenMapper {

    private final UserMapper userMapper;

    RefreshToken mapToDomainEntity(RefreshTokenJpaEntity refreshToken) {
        return RefreshToken.withId(
                new RefreshToken.RefreshTokenId(refreshToken.getId()),
                userMapper.mapToDomainEntity(refreshToken.getUser()),
                refreshToken.getToken(),
                refreshToken.getExpiresAt()
        );
    }

    RefreshTokenJpaEntity mapToJpaEntity(RefreshToken refreshToken) {
        RefreshTokenJpaEntity refreshTokenJpaEntity = new RefreshTokenJpaEntity(
                null,
                userMapper.mapToJpaEntity(refreshToken.getUser()),
                refreshToken.getToken(),
                refreshToken.getExpiresAt()
        );
        refreshToken.getId().ifPresent(refreshTokenId -> refreshTokenJpaEntity.setId(refreshTokenId.value()));
        return refreshTokenJpaEntity;
    }
}
