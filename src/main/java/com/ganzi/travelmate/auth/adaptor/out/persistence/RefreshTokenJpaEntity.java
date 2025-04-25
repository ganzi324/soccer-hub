package com.ganzi.travelmate.auth.adaptor.out.persistence;

import com.ganzi.travelmate.common.infra.persistence.BaseTimeEntity;
import com.ganzi.travelmate.user.adaptor.out.persistence.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(
        name = "refresh_token",
        indexes = {
                @Index(name = "idx_refresh_token_user", columnList = "user_id, token")
        }
)
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Column(nullable = false)
    private Instant expiresAt;
}
