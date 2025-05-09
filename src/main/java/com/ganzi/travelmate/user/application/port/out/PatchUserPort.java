package com.ganzi.travelmate.user.application.port.out;

import com.ganzi.travelmate.user.domain.User;

public interface PatchUserPort {
    void patch(User user);
}
