package com.ganzi.soccerhub.user.adaptor.out.persistence;

import com.ganzi.soccerhub.user.domain.UserRole;
import com.ganzi.soccerhub.user.domain.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"user\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    @Convert(converter = UserRoleConvertor.class)
    private UserRole userRole;

    @Column(nullable = false)
    @Convert(converter = UserTypeConvertor.class)
    private UserType userType;

}
