package com.ganzi.travelmate.user.adaptor.out.persistence;

import com.ganzi.travelmate.common.PersistenceAdapter;
import com.ganzi.travelmate.user.application.port.out.AddUserPort;
import com.ganzi.travelmate.user.application.port.out.LoadUserPort;
import com.ganzi.travelmate.user.application.port.out.PatchUserPort;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
class UserPersistenceAdaptor implements AddUserPort, LoadUserPort, PatchUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        userRepository.save(userJpaEntity);

        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> loadUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::mapToDomainEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> loadUserById(User.UserId id) {
        return userRepository.findById(id.value()).map(userMapper::mapToDomainEntity);
    }

    @Override
    public Optional<User> loadUserByUserKey(String userKey) {
        return userRepository.findByUserKey(userKey).map(userMapper::mapToDomainEntity);
    }

    @Override
    public void patch(User user) {
        userRepository.save(userMapper.mapToJpaEntity(user));
    }
}
