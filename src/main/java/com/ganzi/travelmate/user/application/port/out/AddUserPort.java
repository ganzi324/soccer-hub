package com.ganzi.travelmate.user.application.port.out;

import com.ganzi.travelmate.user.domain.User;

public interface AddUserPort {
    User save(User user);
}
