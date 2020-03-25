package com.springusertrack.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocoderMetaData {
    @JsonProperty("Address")
    private Location location;

    public Location getLocation() {
        return location;
    }
}
