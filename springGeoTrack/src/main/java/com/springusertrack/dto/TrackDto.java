package com.springusertrack.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TrackDto {
    @NotNull
    private double lat;
    @NotNull
    private double lng;
    protected String countryCode;
    protected String country;
    protected String province;
    protected String city;
    protected String street;
    protected String house;
    protected Integer postalCode;
    protected Integer userId;
}
