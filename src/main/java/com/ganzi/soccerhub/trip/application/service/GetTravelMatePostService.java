package com.ganzi.soccerhub.trip.application.service;

import com.ganzi.soccerhub.common.UseCase;
import com.ganzi.soccerhub.trip.application.command.TravelMatePostSearchCriteria;
import com.ganzi.soccerhub.trip.application.port.in.GetTravelMatePostQuery;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetTravelMatePostService implements GetTravelMatePostQuery {

    private final LoadTravelMatePostPort loadTravelMatePostPort;

    @Override
    public Optional<TravelMatePost> getById(TravelMatePost.PostId id) {
        return loadTravelMatePostPort.loadById(id);
    }

    @Override
    public Page<TravelMatePost> search(TravelMatePostSearchCriteria criteria, Pageable pageable) {
        return loadTravelMatePostPort.search(criteria, pageable);
    }
}
