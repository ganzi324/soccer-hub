package com.ganzi.travelmate.auth.adaptor.out.persistence;

import com.ganzi.travelmate.auth.application.port.out.AddRefreshTokenPort;
import com.ganzi.travelmate.auth.application.port.out.LoadRefreshTokenPort;
import com.ganzi.travelmate.auth.domain.RefreshToken;
import com.ganzi.travelmate.common.PersistenceAdapter;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Optional<RefreshToken> loadByUserIdAndToken(User.UserId userId, String token) {
        Optional<RefreshTokenJpaEntity> jpaEntity = refreshTokenRepository.findByUserIdAndToken(userId.value(), token);
        return jpaEntity.map(refreshTokenMapper::mapToDomainEntity);
    }
}
