package com.tasks.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocoderMetaData {
    @JsonProperty("Address")
    private Address address;

    public Address getAddress() {
        return address;
    }
}
