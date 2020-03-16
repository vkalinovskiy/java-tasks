package com.tasks.yandexgeocoder.geocoderStructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocoderMetaData {
    @JsonProperty("Address")
    private Address address;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("precision")
    private String precision;
    @JsonProperty("text")
    private String text;
}
