package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.trip.application.command.AddTravelMateJoinRequestCommand;
import com.ganzi.soccerhub.trip.application.event.TravelMateJoinRequestCreatedEvent;
import com.ganzi.soccerhub.trip.application.exception.PostParticipationNotAllowedException;
import com.ganzi.soccerhub.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.soccerhub.trip.application.port.in.AddTravelMateJoinRequestUseCase;
import com.ganzi.soccerhub.trip.application.port.out.AddTravelMateJoinRequestPort;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.trip.domain.TravelMatePostStatus;
import com.ganzi.soccerhub.user.application.exception.UserNotFoundException;
import com.ganzi.soccerhub.user.application.port.out.LoadUserPort;
import com.ganzi.soccerhub.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
@RequiredArgsConstructor
public class AddTravelMateJoinRequestService implements AddTravelMateJoinRequestUseCase {

    private final LoadTravelMatePostPort loadTravelMatePostPort;
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
