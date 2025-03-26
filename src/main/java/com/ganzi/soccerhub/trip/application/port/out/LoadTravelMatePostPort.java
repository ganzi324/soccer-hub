package com.ganzi.soccerhub.trip.application.port.out;

import com.ganzi.soccerhub.trip.application.command.TravelMatePostSearchCriteria;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoadTravelMatePostPort {

    Optional<TravelMatePost> loadById(TravelMatePost.PostId id);

    Page<TravelMatePost> search(TravelMatePostSearchCriteria criteria, Pageable pageable);
}
