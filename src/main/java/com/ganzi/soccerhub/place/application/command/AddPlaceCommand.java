package com.ganzi.soccerhub.place.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import com.ganzi.soccerhub.place.domain.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddPlaceCommand extends SelfValidating<AddPlaceCommand> {

    @Size(min = 1, max = 20, message = "이름은 1~20 글자여야 합니다.")
    @NotBlank
    private final String name;

    private final String description;

    @Size(min = 1, max = 20)
    @NotBlank
    private final String state;

    @Size(min = 1, max = 50)
    @NotBlank
    private final String city;

    @Size(min = 1, max = 100)
    @NotBlank
    private final String street;

    private final String detailAddress;

    @Size(min = 1, max = 10)
    @NotBlank
    private final String postalCode;

    private final double latitude;

    private final double longitude;

    public AddPlaceCommand(String name, String description, String state, String city, String street, String detailAddress, String postalCode, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.city = city;
        this.street = street;
        this.detailAddress = detailAddress;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;

        this.validateSelf();
    }

    public Address getAddress() {
        return new Address(
          state,
          city,
          street,
          detailAddress,
          postalCode,
          latitude,
          longitude
        );
    }
}
