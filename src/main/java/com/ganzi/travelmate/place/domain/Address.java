package com.ganzi.travelmate.place.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Address {

    private String state;

    private String city;

    private String street;

    private String detailAddress;

    private String postalCode;

    private double latitude;

    private double longitude;

}
