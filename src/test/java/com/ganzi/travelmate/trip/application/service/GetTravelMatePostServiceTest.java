package com.ganzi.travelmate.trip.application.service;

import com.ganzi.travelmate.common.data.TravelMatePostTestData;
import com.ganzi.travelmate.trip.application.port.in.GetTravelMatePostQuery;
import com.ganzi.travelmate.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.travelmate.trip.domain.TravelMatePost;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GetTravelMatePostServiceTest {

    private final LoadTravelMatePostPort loadTravelMatePostPort = Mockito.mock(LoadTravelMatePostPort.class);

    private final GetTravelMatePostQuery getTravelMatePostQuery = new GetTravelMatePostService(loadTravelMatePostPort);

    @Test
    void getTravelMatePostById() {
        givenLoadTravelMatePostByIdWillSucceed();
        assertThat(getTravelMatePostQuery.getById(TravelMatePost.PostId.of(1L))).isNotEmpty();
    }

    @Test
    void getTravelMatePostByNonExistId() {
        givenLoadTravelMatePostByIdWillReturnEmpty();
        assertThat(getTravelMatePostQuery.getById(TravelMatePost.PostId.of(1L))).isEmpty();
    }

    private void givenLoadTravelMatePostByIdWillSucceed() {
        given(loadTravelMatePostPort.loadById(any(TravelMatePost.PostId.class)))
                .willReturn(Optional.of(TravelMatePostTestData.defaultPost()));
    }

    private void givenLoadTravelMatePostByIdWillReturnEmpty() {
        given(loadTravelMatePostPort.loadById(any(TravelMatePost.PostId.class)))
                .willReturn(Optional.empty());
    }

}
