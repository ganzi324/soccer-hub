package com.ganzi.soccerhub.trip.adaptor.in.web;

import com.ganzi.soccerhub.common.infra.hashid.HashId;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchTravelMatePostResponse {

    @HashId
    private Long id;

    public static PatchTravelMatePostResponse of(TravelMatePost.PostId id) {
        return new PatchTravelMatePostResponse(id.value());
    }
}
