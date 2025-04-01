package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.infra.persistence.BaseTimeEntity;
import com.ganzi.soccerhub.trip.domain.RequestStatus;
import com.ganzi.soccerhub.user.adaptor.out.persistence.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import static com.ganzi.soccerhub.trip.domain.TravelMateJoinRequest.MESSAGE_MAX_LENGTH;

@Entity
@Table(name = "travel_mate_join_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelMateJoinRequestJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_mate_post_id", nullable = false)
    private TravelMatePostJpaEntity travelMatePost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private UserJpaEntity requester;

    @Column(length = MESSAGE_MAX_LENGTH)
    private String message;

    @Column(nullable = false)
    @Convert(converter = RequestStatusConvertor.class)
    private RequestStatus status;
}
