package com.ganzi.travelmate.trip.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.place.application.exception.PlaceNotFoundException;
import com.ganzi.travelmate.place.application.port.in.GetPlaceQuery;
import com.ganzi.travelmate.place.domain.Place;
import com.ganzi.travelmate.trip.application.command.AddTravelMatePostCommand;
import com.ganzi.travelmate.trip.application.port.in.AddTravelMatePostUseCase;
import com.ganzi.travelmate.trip.application.port.out.AddTravelMatePostPort;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.user.application.exception.UserNotFoundException;
import com.ganzi.travelmate.user.application.port.in.GetUserQuery;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class AddTravelMatePostService implements AddTravelMatePostUseCase {

    private final AddTravelMatePostPort addTravelMatePostPort;
    private final GetUserQuery getUserQuery;
    private final GetPlaceQuery getPlaceQuery;

    @Override
    public TravelMatePost addTravelMatePost(AddTravelMatePostCommand command) {
        User author = getUserQuery.getUserById(command.getAuthorId()).orElseThrow(UserNotFoundException::new);

        List<Place> placeList = getPlaceList(command.getPlaces());

        TravelMatePost travelMatePost = TravelMatePost.withoutId(
                command.getTitle(),
                command.getStartDate(),
                command.getEndDate(),
                placeList,
                command.getCapacity(),
                command.getGender(),
                command.getAge(),
                command.getDescription(),
                author
        );

        return addTravelMatePostPort.addPost(travelMatePost);
    }

    private List<Place> getPlaceList(List<Long> placeIds) {
        List<Place> placeList = getPlaceQuery.getAllByIds(placeIds.stream().distinct().map(Place.PlaceId::of).toList());
        if (placeList.size() != placeIds.size()) {
            throw new PlaceNotFoundException();
        }
        return placeList;
    }
}
