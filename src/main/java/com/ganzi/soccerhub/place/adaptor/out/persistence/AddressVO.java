package com.ganzi.soccerhub.place.adaptor.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AddressVO {

    @Column(nullable = false, length = 20)
    private String state;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(length = 100)
    private String detailAddress;

    @Column(nullable = false, length = 10)
    private String postalCode;

    private double latitude;

    private double longitude;
}
