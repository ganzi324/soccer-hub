package com.ganzi.soccerhub.user.application.command;

import com.ganzi.soccerhub.user.domain.User;

public record GetRefreshTokenCommand(User.UserId userId, String refreshToken) {
}
