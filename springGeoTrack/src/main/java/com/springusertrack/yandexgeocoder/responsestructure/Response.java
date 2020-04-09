package com.springusertrack.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Response {
    @JsonProperty("GeoObjectCollection")
    private GeoObjectCollection geoObjectCollection;

    public GeoObjectCollection getGeoObjectCollection() {
        return geoObjectCollection;
    }
}
