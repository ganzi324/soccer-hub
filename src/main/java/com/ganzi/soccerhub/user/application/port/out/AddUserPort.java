package com.ganzi.soccerhub.user.application.port.out;

import com.ganzi.soccerhub.user.domain.User;

public interface AddUserPort {
    User save(User user);
}
