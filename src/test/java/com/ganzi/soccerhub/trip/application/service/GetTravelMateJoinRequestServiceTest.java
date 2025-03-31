package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.common.data.TravelMateJoinRequestTestData;
import com.ganzi.soccerhub.common.exception.DomainIdNotFoundException;
import com.ganzi.soccerhub.trip.application.port.in.GetTravelMateJoinRequestQuery;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMateJoinRequestPort;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class GetTravelMateJoinRequestServiceTest {

    private final LoadTravelMateJoinRequestPort loadTravelMateJoinRequestPort = Mockito.mock(LoadTravelMateJoinRequestPort.class);

    private final GetTravelMateJoinRequestQuery getTravelMateJoinRequestQuery = new GetTravelMateJoinRequestService(loadTravelMateJoinRequestPort);

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

    private void givenTravelMatePostRequestLoaded(TravelMateJoinRequest travelMateJoinRequest) {
        given(loadTravelMateJoinRequestPort.loadById(travelMateJoinRequest.getId().get()))
                .willReturn(Optional.of(travelMateJoinRequest));
    }
}
