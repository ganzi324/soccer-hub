package com.ganzi.travelmate.user.adaptor.out.persistence;

import com.ganzi.travelmate.user.domain.User;
import com.ganzi.travelmate.user.domain.User.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToDomainEntity(UserJpaEntity user) {
        return User.withId(
                new UserId(user.getId()),
                user.getUserKey(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPicture(),
                user.getUserRole(),
                user.getUserType()
        );
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        return new UserJpaEntity(
                user.getId().map(UserId::value).orElse(null),
                user.getUserKey(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPicture(),
                user.getUserRole(),
                user.getUserType()
        );
    }

}
