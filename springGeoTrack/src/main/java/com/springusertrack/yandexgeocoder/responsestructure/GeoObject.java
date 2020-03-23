package com.springusertrack.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GeoObject {
    @JsonProperty("metaDataProperty")
    private MetaDataProperty metaDataProperty;

    public MetaDataProperty getMetaDataProperty() {
        return metaDataProperty;
    }
}
