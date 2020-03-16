package com.tasks.yandexgeocoder.geocoderStructure;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GeoObject {
    @JsonProperty("metaDataProperty")
    private MetaDataProperty metaDataProperty;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
}
