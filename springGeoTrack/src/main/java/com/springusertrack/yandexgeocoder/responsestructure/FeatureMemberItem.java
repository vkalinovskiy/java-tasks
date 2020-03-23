package com.springusertrack.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeatureMemberItem {
    @JsonProperty("GeoObject")
    private GeoObject geoObject;

    public GeoObject getGeoObject() {
        return geoObject;
    }
}
