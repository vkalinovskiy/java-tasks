package com.tasks.yandexgeocoder.geocoderStructure;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Address {
    @JsonProperty("Components")
    private List<ComponentsItem> components;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("formatted")
    private String formatted;
    @JsonProperty("postal_code")
    private String postalCode;
}
