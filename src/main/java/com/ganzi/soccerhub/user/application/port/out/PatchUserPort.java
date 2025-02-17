package com.ganzi.soccerhub.user.application.port.out;

import com.ganzi.soccerhub.user.domain.User;

public interface PatchUserPort {
    void patch(User user);
}
