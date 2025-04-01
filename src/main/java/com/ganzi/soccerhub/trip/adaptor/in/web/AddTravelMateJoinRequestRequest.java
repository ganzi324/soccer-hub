package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.ganzi.soccerhub.common.infra.hashid.HashId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class AddTravelMateJoinRequestRequest {

    @HashId
    private Long postId;

    private String message;
}
