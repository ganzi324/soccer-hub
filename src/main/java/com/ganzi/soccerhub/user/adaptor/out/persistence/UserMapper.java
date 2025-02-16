package com.ganzi.soccerhub.user.adaptor.out.persistence;

import com.ganzi.soccerhub.user.domain.User;
import com.ganzi.soccerhub.user.domain.User.UserId;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    User mapToDomainEntity(UserJpaEntity user) {
        return User.withId(
                new UserId(user.getId()),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPicture(),
                user.getUserRole(),
                user.getUserType()
        );
    }

    UserJpaEntity mapToJpaEntity(User user) {
        UserJpaEntity userJpaEntity = new UserJpaEntity(
                null,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPicture(),
                user.getUserRole(),
                user.getUserType()
        );
        user.getId().ifPresent(userId -> userJpaEntity.setId(userId.value()));

        return userJpaEntity;
    }

}
