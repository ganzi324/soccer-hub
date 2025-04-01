package com.ganzi.soccerhub.trip.application.domain;

import com.ganzi.soccerhub.common.data.TravelMateJoinRequestTestData;
import com.ganzi.soccerhub.common.exception.InvalidStatusTransitionException;
import com.ganzi.soccerhub.trip.domain.RequestStatus;
import com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TravelMateJoinRequestTest {

    @Test
    void approve_willSuccess() {
        TravelMateJoinRequest joinRequest = TravelMateJoinRequestTestData.pending();

        joinRequest.approve();
        assertThat(joinRequest.getStatus()).isEqualTo(RequestStatus.APPROVED);
    }

    @Test
    void approve_shouldFail_ifStatusIsNotPending() {
        TravelMateJoinRequest joinRequest = TravelMateJoinRequestTestData.approved();

        assertThatThrownBy(joinRequest::approve)
                .isExactlyInstanceOf(InvalidStatusTransitionException.class)
                .hasFieldOrPropertyWithValue("details", "Cannot change from current state APPROVED to state APPROVED.");
    }

    @Test
    void request_shouldEquals() {
        assertThat(TravelMateJoinRequestTestData.pending().equals(TravelMateJoinRequestTestData.pendingSame())).isTrue();
    }
}
