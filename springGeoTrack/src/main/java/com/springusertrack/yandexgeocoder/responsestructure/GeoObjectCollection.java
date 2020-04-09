package com.springusertrack.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeoObjectCollection {

    @JsonProperty("featureMember")
    private List<FeatureMemberItem> featureMember;

    public FeatureMemberItem getFeatureMember() {
        return featureMember.get(0);
    }
}
