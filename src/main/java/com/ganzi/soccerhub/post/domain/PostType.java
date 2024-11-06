package com.ganzi.soccerhub.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostType {

    NOTICE("notice", "공지"),
    MATCH("match", "경기"),
    SCHEDULE("schedule", "일정"),
    GENERAL("general", "일반");

    private final String key;
    private final String title;
}
