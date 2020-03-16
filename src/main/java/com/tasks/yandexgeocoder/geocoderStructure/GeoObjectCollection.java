package com.tasks.yandexgeocoder.geocoderStructure;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class GeoObjectCollection {
    @JsonProperty("featureMember")
    private List<FeatureMemberItem> featureMember;
}
