package com.ganzi.soccerhub.auth.adaptor.out.persistence;

import com.ganzi.soccerhub.auth.application.port.out.AddRefreshTokenPort;
import com.ganzi.soccerhub.auth.domain.RefreshToken;
import com.ganzi.soccerhub.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RefreshTokenPersistenceAdaptor implements AddRefreshTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenJpaEntity refreshTokenJpaEntity = refreshTokenMapper.mapToJpaEntity(refreshToken);
        refreshTokenRepository.save(refreshTokenJpaEntity);

        return refreshTokenMapper.mapToDomainEntity(refreshTokenJpaEntity);
    }
}
