package com.ganzi.travelmate.trip.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.trip.application.command.AddTravelMateJoinRequestCommand;
import com.ganzi.travelmate.trip.application.event.TravelMateJoinRequestCreatedEvent;
import com.ganzi.travelmate.trip.application.exception.PostParticipationNotAllowedException;
import com.ganzi.travelmate.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.travelmate.trip.application.port.in.AddTravelMateJoinRequestUseCase;
import com.ganzi.travelmate.trip.application.port.out.AddTravelMateJoinRequestPort;
import com.ganzi.travelmate.trip.application.port.out.LoadTravelMateJoinRequestPort;
import com.ganzi.travelmate.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.travelmate.trip.domain.TravelMateJoinRequest;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.trip.domain.TravelMatePostStatus;
import com.ganzi.travelmate.user.application.exception.UserNotFoundException;
import com.ganzi.travelmate.user.application.port.out.LoadUserPort;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
@RequiredArgsConstructor
public class AddTravelMateJoinRequestService implements AddTravelMateJoinRequestUseCase {

    private final LoadTravelMatePostPort loadTravelMatePostPort;
    private final LoadTravelMateJoinRequestPort loadTravelMateJoinRequestPort;
    private final AddTravelMateJoinRequestPort addTravelMateJoinRequestPort;
    private final LoadUserPort loadUserPort;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * @apiNote 추후 Post 조건에 부합하는 사용자만 승인 가능 여부
     */
    @Override
    public TravelMateJoinRequest add(AddTravelMateJoinRequestCommand command) {
        TravelMatePost travelMatePost = loadTravelMatePostPort.loadById(command.getTravelMatePostId()).orElseThrow(TravelMatePostNotFoundException::new);

        if (travelMatePost.getStatus() != TravelMatePostStatus.OPEN) {
            throw new PostParticipationNotAllowedException("The posting status is either CLOSED or PENDING.");
        }

        User requester = loadUserPort.loadUserById(command.getRequesterId()).orElseThrow(UserNotFoundException::new);

        loadTravelMateJoinRequestPort.loadByPostIdAndRequesterId(command.getTravelMatePostId(), command.getRequesterId())
                .ifPresent(request -> {
                    throw new PostParticipationNotAllowedException("The user has already been requested to participate in a posting");
                });

        TravelMateJoinRequest travelMateJoinRequest = addTravelMateJoinRequestPort.add(TravelMateJoinRequest.withoutId(travelMatePost, requester, command.getMessage()));

        sendNotification(travelMateJoinRequest);

        return travelMateJoinRequest;
    }

    private void sendNotification(TravelMateJoinRequest travelMateJoinRequest) {
        eventPublisher.publishEvent(
                new TravelMateJoinRequestCreatedEvent(travelMateJoinRequest)
        );
    }
}
