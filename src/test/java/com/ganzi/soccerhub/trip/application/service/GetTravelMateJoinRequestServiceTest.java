package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.common.data.TravelMateJoinRequestTestData;
import com.ganzi.soccerhub.common.exception.DomainIdNotFoundException;
import com.ganzi.soccerhub.common.exception.UnauthorizedException;
import com.ganzi.soccerhub.trip.application.port.in.GetTravelMateJoinRequestQuery;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMateJoinRequestPort;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.RequestStatus;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class GetTravelMateJoinRequestServiceTest {

    private final LoadTravelMateJoinRequestPort loadTravelMateJoinRequestPort = Mockito.mock(LoadTravelMateJoinRequestPort.class);
    private final LoadTravelMatePostPort loadTravelMatePostPort = Mockito.mock(LoadTravelMatePostPort.class);;

    private final GetTravelMateJoinRequestQuery getTravelMateJoinRequestQuery = new GetTravelMateJoinRequestService(loadTravelMateJoinRequestPort, loadTravelMatePostPort);

    @Test
    void getTravelMatePostById_willSuccess() {
        TravelMateJoinRequest travelMateJoinRequest = TravelMateJoinRequestTestData.approved();

        givenTravelMatePostRequestLoaded(travelMateJoinRequest);

        getTravelMateJoinRequestQuery.getById(travelMateJoinRequest.getId().get());

        verify(loadTravelMateJoinRequestPort).loadById(travelMateJoinRequest.getId().get());
    }

    @Test
    void getTravelMatePostById_shouldFailed_whenIdIncorrect() {
        TravelMateJoinRequest travelMateJoinRequest = TravelMateJoinRequestTestData.approved();

        givenTravelMatePostRequestLoaded(travelMateJoinRequest);

        assertThatThrownBy(() -> getTravelMateJoinRequestQuery.getById(TravelMateJoinRequest.Id.of(10L)))
                .isExactlyInstanceOf(DomainIdNotFoundException.class);
    }

    @Test
    void getByPost_willSuccess() {
        TravelMateJoinRequest travelMateJoinRequest = TravelMateJoinRequestTestData.approved();

        givenTravelMatePostLoaded(travelMateJoinRequest.getTravelMatePost());
        givenTravelMateJoinRequestLoaded(travelMateJoinRequest);

        List<TravelMateJoinRequest> result = getTravelMateJoinRequestQuery.getByPost(
                travelMateJoinRequest.getTravelMatePost().getId().get(),
                travelMateJoinRequest.getStatus(),
                travelMateJoinRequest.getRequester().getId().get()
        );

        assertNotNull(result);

        verify(loadTravelMateJoinRequestPort).loadByPost(any(), any());
    }

    @Test
    void getByPost_shouldFailed_whenTravelMatePostNotFound() {
        TravelMateJoinRequest travelMateJoinRequest = TravelMateJoinRequestTestData.approved();

        givenTravelMatePostLoaded(travelMateJoinRequest.getTravelMatePost());
        givenTravelMateJoinRequestLoaded(travelMateJoinRequest);

        assertThatThrownBy(() -> getTravelMateJoinRequestQuery.getByPost(
                TravelMatePost.PostId.of(10L),
                travelMateJoinRequest.getStatus(),
                travelMateJoinRequest.getRequester().getId().get()
        )).isExactlyInstanceOf(DomainIdNotFoundException.class);
    }

    @Test
    void getByPost_shouldFailed_whenUserIsNotPostAuthor() {
        TravelMateJoinRequest travelMateJoinRequest = TravelMateJoinRequestTestData.approved();

        givenTravelMatePostLoaded(travelMateJoinRequest.getTravelMatePost());
        givenTravelMateJoinRequestLoaded(travelMateJoinRequest);

        assertThatThrownBy(() -> getTravelMateJoinRequestQuery.getByPost(
                travelMateJoinRequest.getTravelMatePost().getId().get(),
                travelMateJoinRequest.getStatus(),
                User.UserId.of(10L)
        )).isExactlyInstanceOf(UnauthorizedException.class);
    }

    private void givenTravelMatePostRequestLoaded(TravelMateJoinRequest travelMateJoinRequest) {
        given(loadTravelMateJoinRequestPort.loadById(travelMateJoinRequest.getId().get()))
                .willReturn(Optional.of(travelMateJoinRequest));
    }

    private void givenTravelMatePostLoaded(TravelMatePost travelMatePost) {
        given(loadTravelMatePostPort.loadById(travelMatePost.getId().get()))
                .willReturn(Optional.of(travelMatePost));
    }

    private void givenTravelMateJoinRequestLoaded(TravelMateJoinRequest travelMateJoinRequest) {
        given(loadTravelMateJoinRequestPort.loadByPost(any(TravelMatePost.PostId.class), any(RequestStatus.class)))
                .willReturn(List.of(travelMateJoinRequest));
    }
}
