package com.tasks.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaDataProperty {
    @JsonProperty("GeocoderMetaData")
    private GeocoderMetaData geocoderMetaData;

    public GeocoderMetaData getGeocoderMetaData() {
        return geocoderMetaData;
    }
}
