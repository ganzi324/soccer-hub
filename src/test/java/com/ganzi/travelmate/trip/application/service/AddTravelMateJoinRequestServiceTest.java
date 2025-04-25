package com.ganzi.travelmate.trip.application.service;

import com.ganzi.travelmate.common.data.TravelMateJoinRequestTestData;
import com.ganzi.travelmate.common.data.TravelMatePostTestData;
import com.ganzi.travelmate.trip.application.command.AddTravelMateJoinRequestCommand;
import com.ganzi.travelmate.trip.application.exception.PostParticipationNotAllowedException;
import com.ganzi.travelmate.trip.application.port.in.AddTravelMateJoinRequestUseCase;
import com.ganzi.travelmate.trip.application.port.out.AddTravelMateJoinRequestPort;
import com.ganzi.travelmate.trip.application.port.out.LoadTravelMateJoinRequestPort;
import com.ganzi.travelmate.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.application.port.out.LoadUserPort;
import com.ganzi.travelmate.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AddTravelMateJoinRequestServiceTest {

    private final LoadTravelMatePostPort loadTravelMatePostPort = Mockito.mock(LoadTravelMatePostPort.class);
    private final LoadTravelMateJoinRequestPort loadTravelMateJoinRequestPort = Mockito.mock(LoadTravelMateJoinRequestPort.class);
    private final AddTravelMateJoinRequestPort addTravelMateJoinRequestPort = Mockito.mock(AddTravelMateJoinRequestPort.class);
    private final LoadUserPort loadUserPort = Mockito.mock(LoadUserPort.class);
    private final ApplicationEventPublisher applicationEventPublisher = Mockito.mock(ApplicationEventPublisher.class);

    private final AddTravelMateJoinRequestUseCase addTravelMateJoinRequestUseCase = new AddTravelMateJoinRequestService(
            loadTravelMatePostPort,
            loadTravelMateJoinRequestPort,
            addTravelMateJoinRequestPort,
            loadUserPort,
            applicationEventPublisher
    );

    @Test
    void add_shouldSucceed() {
        TravelMateJoinRequest joinRequest = TravelMateJoinRequestTestData.pending();

        givenTravelMatePostLoaded(joinRequest.getTravelMatePost());
        givenRequesterLoaded(joinRequest.getRequester());
        givenTravelMateRequestJoinAdded(joinRequest);

        AddTravelMateJoinRequestCommand command = new AddTravelMateJoinRequestCommand(joinRequest.getTravelMatePost().getId().get(), joinRequest.getRequester().getId().get(), "I want to join!");

        TravelMateJoinRequest result = addTravelMateJoinRequestUseCase.add(command);

        assertNotNull(result);
        verify(addTravelMateJoinRequestPort).add(any());
    }

    @Test
    void add_throwsException_whenTravelMatePostStatusNotOpen() {
        TravelMateJoinRequest joinRequest = TravelMateJoinRequestTestData.pending();
        TravelMatePost travelMatePost = TravelMatePostTestData.closed();

        givenTravelMatePostLoaded(travelMatePost);
        givenRequesterLoaded(joinRequest.getRequester());
        givenTravelMateRequestJoinAdded(joinRequest);

        AddTravelMateJoinRequestCommand command = new AddTravelMateJoinRequestCommand(travelMatePost.getId().get(), joinRequest.getRequester().getId().get(), "I want to join!");

        assertThatThrownBy(() -> addTravelMateJoinRequestUseCase.add(command))
                .isExactlyInstanceOf(PostParticipationNotAllowedException.class);
    }

    private void givenTravelMatePostLoaded(TravelMatePost travelMatePost) {
        given(loadTravelMatePostPort.loadById(travelMatePost.getId().get()))
                .willReturn(Optional.of(travelMatePost));
    }

    private void givenRequesterLoaded(User requester) {
        given(loadUserPort.loadUserById(requester.getId().get()))
                .willReturn(Optional.of(requester));
    }

    private void givenTravelMateRequestJoinAdded(TravelMateJoinRequest travelMateJoinRequest) {
        given(addTravelMateJoinRequestPort.add(any()))
                .willReturn(travelMateJoinRequest);
    }
}
