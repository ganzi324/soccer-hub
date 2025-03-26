package com.ganzi.soccerhub.trip.application.port.in;

import com.ganzi.soccerhub.trip.application.command.TravelMatePostSearchCriteria;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GetTravelMatePostQuery {

    Optional<TravelMatePost> getById(TravelMatePost.PostId id);

    Page<TravelMatePost> search(TravelMatePostSearchCriteria criteria, Pageable pageable);
}
