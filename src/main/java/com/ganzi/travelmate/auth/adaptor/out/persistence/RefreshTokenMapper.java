package com.ganzi.travelmate.auth.adaptor.out.persistence;

import com.ganzi.travelmate.auth.domain.RefreshToken;
import com.ganzi.travelmate.user.adaptor.out.persistence.UserMapper;
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
        return new RefreshTokenJpaEntity(
                refreshToken.getId().map(RefreshToken.RefreshTokenId::value).orElse(null),
                userMapper.mapToJpaEntity(refreshToken.getUser()),
                refreshToken.getToken(),
                refreshToken.getExpiresAt()
        );
    }
}
