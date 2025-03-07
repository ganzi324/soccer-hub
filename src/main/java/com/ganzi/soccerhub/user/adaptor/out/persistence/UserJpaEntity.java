package com.ganzi.soccerhub.user.adaptor.out.persistence;

import com.ganzi.soccerhub.common.infra.persistence.BaseTimeEntity;
import com.ganzi.soccerhub.user.domain.UserRole;
import com.ganzi.soccerhub.user.domain.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userKey;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String picture;

    @Column(nullable = false)
    @Convert(converter = UserRoleConvertor.class)
    private UserRole userRole;

    @Column(nullable = false)
    @Convert(converter = UserTypeConvertor.class)
    private UserType userType;

}
