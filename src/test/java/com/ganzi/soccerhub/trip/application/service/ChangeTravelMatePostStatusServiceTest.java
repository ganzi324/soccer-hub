package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.common.data.TravelMatePostTestData;
import com.ganzi.soccerhub.common.exception.DomainModificationDeniedException;
import com.ganzi.soccerhub.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.soccerhub.trip.application.port.in.ChangeTravelMatePostStatusUseCase;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.soccerhub.trip.application.port.out.PatchTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.trip.domain.TravelMatePostStatus;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class ChangeTravelMatePostStatusServiceTest {

    private final LoadTravelMatePostPort loadTravelMatePostPort = Mockito.mock(LoadTravelMatePostPort.class);
    private final PatchTravelMatePostPort patchTravelMatePostPort = Mockito.mock(PatchTravelMatePostPort.class);

    private final ChangeTravelMatePostStatusUseCase changeTravelMatePostStatusUseCase = new ChangeTravelMatePostStatusService(loadTravelMatePostPort, patchTravelMatePostPort);

    @Test
    void changeStatus() {
        TravelMatePost travelMatePost = TravelMatePostTestData.defaultPost();
        TravelMatePostStatus newStatus = TravelMatePostStatus.CLOSED;

        givenTravelMatePostLoaded(travelMatePost);
        givenTravelMatePostStatusUpdated(1);

        TravelMatePost updatedTravelMatePost = changeTravelMatePostStatusUseCase.changeStatus(newStatus, travelMatePost.getId().get(), travelMatePost.getAuthor().getId().get());

        assertThat(updatedTravelMatePost.getStatus()).isEqualTo(newStatus);
    }

    @Test
    void changeStatus_throwsException_ifUnauthorizedUserAttemptsUpdate() {
        TravelMatePost travelMatePost = TravelMatePostTestData.defaultPost();
        TravelMatePostStatus newStatus = TravelMatePostStatus.CLOSED;

        givenTravelMatePostLoaded(travelMatePost);
        givenTravelMatePostStatusUpdated(1);

        assertThatThrownBy(() -> changeTravelMatePostStatusUseCase.changeStatus(newStatus, travelMatePost.getId().get(), User.UserId.of(10L)))
                .isExactlyInstanceOf(DomainModificationDeniedException.class);
    }

    @Test
    void changeStatus_throwsException_whenPersistenceUpdateFails() {
        TravelMatePost travelMatePost = TravelMatePostTestData.defaultPost();
        TravelMatePostStatus newStatus = TravelMatePostStatus.CLOSED;

        givenTravelMatePostLoaded(travelMatePost);
        givenTravelMatePostStatusUpdated(0);

        assertThatThrownBy(() -> changeTravelMatePostStatusUseCase.changeStatus(newStatus, travelMatePost.getId().get(), travelMatePost.getAuthor().getId().get()))
                .isExactlyInstanceOf(TravelMatePostNotFoundException.class);
    }

    private void givenTravelMatePostLoaded(TravelMatePost travelMatePost) {
        given(loadTravelMatePostPort.loadById(any(TravelMatePost.PostId.class)))
                .willReturn(Optional.of(travelMatePost));
    }

    private void givenTravelMatePostStatusUpdated(int updatedRows) {
        given(patchTravelMatePostPort.updateStatus(any(TravelMatePost.PostId.class), any(TravelMatePostStatus.class)))
                .willReturn(updatedRows);
    }
}
