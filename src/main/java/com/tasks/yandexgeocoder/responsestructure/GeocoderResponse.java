package com.tasks.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocoderResponse {
    @JsonProperty("response")
    private Response response;

    public Address getAddress() {
        return response
                .getGeoObjectCollection()
                .getFeatureMember()
                .getGeoObject()
                .getMetaDataProperty()
                .getGeocoderMetaData()
                .getAddress();
    }
}
