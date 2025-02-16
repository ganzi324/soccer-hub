package com.ganzi.soccerhub.user.adaptor.out.persistence;

import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.user.application.port.out.AddUserPort;
import com.ganzi.soccerhub.user.application.port.out.LoadUserPort;
import com.ganzi.soccerhub.user.application.port.out.PatchUserPort;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
class UserPersistenceAdaptor implements AddUserPort, LoadUserPort, PatchUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserJpaEntity userEntity = new UserJpaEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setPicture(user.getPicture());
        userEntity.setUserRole(user.getUserRole());
        userEntity.setUserType(user.getUserType());

        userRepository.save(userEntity);

        return User.withId(
                new User.UserId(userEntity.getId()),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getPicture(),
                userEntity.getUserRole(),
                userEntity.getUserType()
            );
    }

    @Override
    public Optional<User> loadUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::mapToDomainEntity);
    }

    @Override
    public Optional<User> loadUserById(Long id) {
        return userRepository.findById(id).map(userMapper::mapToDomainEntity);
    }

    @Override
    public void patch(User user) {
        userRepository.save(userMapper.mapToJpaEntity(user));
    }
}
