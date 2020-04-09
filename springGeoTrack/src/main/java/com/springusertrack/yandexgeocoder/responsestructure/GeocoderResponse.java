package com.springusertrack.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocoderResponse {
    @JsonProperty("response")
    private Response response;

    public Location getLocation() {
        return response
                .getGeoObjectCollection()
                .getFeatureMember()
                .getGeoObject()
                .getMetaDataProperty()
                .getGeocoderMetaData()
                .getLocation();
    }
}
