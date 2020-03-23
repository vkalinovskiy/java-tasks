package com.springusertrack.yandexgeocoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    protected String countryCode;
    protected String country;
    protected String province;
    protected String city;
    protected String street;
    protected String house;
    protected Integer postalCode;
}
