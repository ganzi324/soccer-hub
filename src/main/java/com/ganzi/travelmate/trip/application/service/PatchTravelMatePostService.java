package com.ganzi.travelmate.trip.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.common.exception.DomainModificationDeniedException;
import com.ganzi.travelmate.place.application.exception.PlaceNotFoundException;
import com.ganzi.travelmate.place.application.port.in.GetPlaceQuery;
import com.ganzi.travelmate.place.domain.Place;
import com.ganzi.travelmate.trip.application.command.PatchTravelMatePostCommand;
import com.ganzi.travelmate.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.travelmate.trip.application.port.in.GetTravelMatePostQuery;
import com.ganzi.travelmate.trip.application.port.in.PatchTravelMatePostUseCase;
import com.ganzi.travelmate.trip.application.port.out.PatchTravelMatePostPort;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.trip.domain.TravelMatePostUpdateVo;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class PatchTravelMatePostService implements PatchTravelMatePostUseCase {

    private final GetTravelMatePostQuery getTravelMatePostQuery;
    private final PatchTravelMatePostPort patchTravelMatePostPort;
    private final GetPlaceQuery getPlaceQuery;

    @Override
    public TravelMatePost patch(PatchTravelMatePostCommand command, User.UserId userId) {
        TravelMatePost targetTravelMatePost = getTravelMatePostQuery.getById(command.getId())
                .orElseThrow(TravelMatePostNotFoundException::new);

        if (!targetTravelMatePost.getAuthor().getId().get().equals(userId)) {
            throw new DomainModificationDeniedException(TravelMatePost.class.getSimpleName(), "Only the author can edit this post.");
        }

        List<Place> placeList = getPlaceList(command.getPlaces());

        TravelMatePostUpdateVo updateVo = new TravelMatePostUpdateVo(
                command.getTitle(),
                command.getStartDate(),
                command.getEndDate(),
                placeList,
                command.getCapacity(),
                command.getGender(),
                command.getAge(),
                command.getDescription()
        );
        TravelMatePost updatedTravelMatePost = targetTravelMatePost.update(updateVo);

        return patchTravelMatePostPort.patch(updatedTravelMatePost);
    }

    private List<Place> getPlaceList(List<Long> placeIds) {
        List<Place> placeList = getPlaceQuery.getAllByIds(placeIds.stream().distinct().map(Place.PlaceId::of).toList());
        if (placeList.size() != placeIds.size()) {
            throw new PlaceNotFoundException();
        }
        return placeList;
    }
}
