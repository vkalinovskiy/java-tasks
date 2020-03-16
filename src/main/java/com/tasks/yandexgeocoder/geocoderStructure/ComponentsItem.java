package com.tasks.yandexgeocoder.geocoderStructure;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ComponentsItem {
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("name")
    private String name;
}
