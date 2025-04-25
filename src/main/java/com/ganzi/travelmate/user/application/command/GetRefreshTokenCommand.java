package com.ganzi.travelmate.user.application.command;

import com.ganzi.travelmate.user.domain.User;

public record GetRefreshTokenCommand(User.UserId userId, String refreshToken) {
}
