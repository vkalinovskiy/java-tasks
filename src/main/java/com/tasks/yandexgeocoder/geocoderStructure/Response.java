package com.tasks.yandexgeocoder.geocoderStructure;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Response {
    @JsonProperty("GeoObjectCollection")
    private GeoObjectCollection geoObjectCollection;
}
