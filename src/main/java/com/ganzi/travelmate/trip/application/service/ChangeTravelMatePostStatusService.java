package com.ganzi.travelmate.trip.application.service;

import com.ganzi.travelmate.common.UseCase;
import com.ganzi.travelmate.common.exception.DomainModificationDeniedException;
import com.ganzi.travelmate.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.travelmate.trip.application.port.in.ChangeTravelMatePostStatusUseCase;
import com.ganzi.travelmate.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.travelmate.trip.application.port.out.PatchTravelMatePostPort;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import com.ganzi.travelmate.trip.domain.TravelMatePostStatus;
import com.ganzi.travelmate.user.domain.User;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ChangeTravelMatePostStatusService implements ChangeTravelMatePostStatusUseCase {

    private final LoadTravelMatePostPort loadTravelMatePostPort;
    private final PatchTravelMatePostPort patchTravelMatePostPort;

    @Override
    public TravelMatePost changeStatus(TravelMatePostStatus newStatus, TravelMatePost.PostId postId, User.UserId userId) {
        TravelMatePost travelMatePost = loadTravelMatePostPort.loadById(postId).orElseThrow(TravelMatePostNotFoundException::new);

        if (!travelMatePost.getAuthor().getId().get().equals(userId)) {
            throw new DomainModificationDeniedException(travelMatePost.getClass().getSimpleName(), "Only the author can edit this post.");
        }

        TravelMatePost changedTravelMatePost = travelMatePost.changeStatus(newStatus);
        saveUpdatedStatus(changedTravelMatePost);

        return changedTravelMatePost;
    }

    private void saveUpdatedStatus(TravelMatePost travelMatePost) {
        int updatedRows = patchTravelMatePostPort.updateStatus(travelMatePost.getId().get(), travelMatePost.getStatus());
        if (updatedRows == 0) {
            throw new TravelMatePostNotFoundException();
        }
    }
}
