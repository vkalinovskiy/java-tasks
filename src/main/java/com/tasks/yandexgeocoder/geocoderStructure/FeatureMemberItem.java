package com.tasks.yandexgeocoder.geocoderStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tasks.yandexgeocoder.geocoderStructure.GeoObject;


public class FeatureMemberItem {
    @JsonProperty("GeoObject")
    private GeoObject geoObject;
}
