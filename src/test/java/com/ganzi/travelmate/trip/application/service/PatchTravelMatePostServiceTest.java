package com.ganzi.travelmate.trip.application.service;

import com.ganzi.travelmate.common.data.TravelMatePostTestData;
import com.ganzi.travelmate.common.exception.DomainModificationDeniedException;
import com.ganzi.travelmate.place.application.port.in.GetPlaceQuery;
import com.ganzi.travelmate.trip.application.command.PatchTravelMatePostCommand;
import com.ganzi.travelmate.trip.application.port.in.GetTravelMatePostQuery;
import com.ganzi.travelmate.trip.application.port.in.PatchTravelMatePostUseCase;
import com.ganzi.travelmate.trip.application.port.out.PatchTravelMatePostPort;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class PatchTravelMatePostServiceTest {

    private final GetTravelMatePostQuery getTravelMatePostQuery = Mockito.mock(GetTravelMatePostQuery.class);
    private final PatchTravelMatePostPort patchTravelMatePostPort = Mockito.mock(PatchTravelMatePostPort.class);
    private final GetPlaceQuery getPlaceQuery = Mockito.mock(GetPlaceQuery.class);

    private final PatchTravelMatePostUseCase patchTravelMatePostUseCase = new PatchTravelMatePostService(getTravelMatePostQuery, patchTravelMatePostPort, getPlaceQuery);

    @Test
    void attemptUserNotMatchAuthor() {
        TravelMatePost travelMatePost = TravelMatePostTestData.defaultPost();

        givenGetTravelMatePostWillSucceed(travelMatePost);
        givenPathTravelMatePostWillSucceed(travelMatePost);

        PatchTravelMatePostCommand command = new PatchTravelMatePostCommand(
                travelMatePost.getId().get(),
                travelMatePost.getTitle(),
                travelMatePost.getStartDate(),
                travelMatePost.getEndDate(),
                travelMatePost.getPlaces().stream().map(place -> place.getId().get().value()).toList(),
                travelMatePost.getCapacity(),
                travelMatePost.getGender(),
                travelMatePost.getAge(),
                travelMatePost.getDescription()
        );

        User.UserId differentUserId = User.UserId.of(100L);

        assertThatThrownBy(() -> patchTravelMatePostUseCase.patch(command, differentUserId))
                .isExactlyInstanceOf(DomainModificationDeniedException.class);
    }

    private void givenGetTravelMatePostWillSucceed(TravelMatePost travelMatePost) {
        given(getTravelMatePostQuery.getById(any(TravelMatePost.PostId.class)))
                .willReturn(Optional.ofNullable(travelMatePost));
    }

    private void givenPathTravelMatePostWillSucceed(TravelMatePost travelMatePost) {
        given(patchTravelMatePostPort.patch(any(TravelMatePost.class)))
                .willReturn(travelMatePost);
    }
}
