package com.ganzi.soccerhub.auth.adaptor.out.persistence;

import com.ganzi.soccerhub.auth.application.port.out.AddRefreshTokenPort;
import com.ganzi.soccerhub.auth.application.port.out.LoadRefreshTokenPort;
import com.ganzi.soccerhub.auth.domain.RefreshToken;
import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class RefreshTokenPersistenceAdaptor implements AddRefreshTokenPort, LoadRefreshTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenJpaEntity refreshTokenJpaEntity = refreshTokenMapper.mapToJpaEntity(refreshToken);
        refreshTokenRepository.save(refreshTokenJpaEntity);

        return refreshTokenMapper.mapToDomainEntity(refreshTokenJpaEntity);
    }

    @Override
    public Optional<RefreshToken> loadByUserIdAndToken(User.UserId userId, String token) {
        Optional<RefreshTokenJpaEntity> jpaEntity = refreshTokenRepository.findByUserIdAndToken(userId.value(), token);
        return jpaEntity.map(refreshTokenMapper::mapToDomainEntity);
    }
}
