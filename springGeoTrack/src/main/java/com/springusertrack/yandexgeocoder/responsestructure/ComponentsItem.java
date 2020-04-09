package com.springusertrack.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ComponentsItem {
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("name")
    private String name;

    public String getKind() {
        return kind;
    }
    public String getName() {
        return name;
    }
}
