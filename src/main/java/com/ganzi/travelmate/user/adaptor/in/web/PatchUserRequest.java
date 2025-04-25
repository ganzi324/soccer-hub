package com.ganzi.travelmate.user.adaptor.in.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchUserRequest {

    private final String name;

    private final String picture;
}
