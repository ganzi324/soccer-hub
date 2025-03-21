package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.common.data.TravelMatePostTestData;
import com.ganzi.soccerhub.place.application.port.in.GetPlaceQuery;
import com.ganzi.soccerhub.trip.application.command.AddTravelMatePostCommand;
import com.ganzi.soccerhub.trip.application.port.in.AddTravelMatePostUseCase;
import com.ganzi.soccerhub.trip.application.port.out.AddTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class AddTravelMatePostServiceTest {

    private final AddTravelMatePostPort addTravelMatePostPort = Mockito.mock(AddTravelMatePostPort.class);
    private final GetUserQuery getUserQuery = Mockito.mock(GetUserQuery.class);
    private final GetPlaceQuery getPlaceQuery = Mockito.mock(GetPlaceQuery.class);

    private final AddTravelMatePostUseCase addTravelMatePostUseCase = new AddTravelMatePostService(addTravelMatePostPort, getUserQuery, getPlaceQuery);

    @Test
    void setAddTravelMatePostPortWillSuccess() {
        TravelMatePost travelMatePost = TravelMatePostTestData.withoutId();

        givenSavePostWillSucceed();
        givenGetUserWillSucceed(travelMatePost.getAuthor());

        AddTravelMatePostCommand command = new AddTravelMatePostCommand(
                travelMatePost.getTitle(),
                travelMatePost.getStartDate(),
                travelMatePost.getEndDate(),
                travelMatePost.getPlaces().stream().map(place -> place.getId().get().value()).toList(),
                travelMatePost.getCapacity(),
                travelMatePost.getGender(),
                travelMatePost.getAge(),
                travelMatePost.getDescription(),
                travelMatePost.getAuthor().getId().get()
        );
        TravelMatePost savedTravelMatePost = addTravelMatePostUseCase.addTravelMatePost(command);
        assertThat(savedTravelMatePost.equals(travelMatePost)).isTrue();
    }

    private void givenSavePostWillSucceed() {
        given(addTravelMatePostPort.addPost(any(TravelMatePost.class)))
                .willReturn(TravelMatePostTestData.defaultPost());
    }

    private void givenGetUserWillSucceed(User user) {
        given(getUserQuery.getUserById(any(User.UserId.class)))
                .willReturn(Optional.ofNullable(user));
    }
}
