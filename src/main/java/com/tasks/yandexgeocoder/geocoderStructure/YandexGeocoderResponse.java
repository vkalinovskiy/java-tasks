package com.tasks.yandexgeocoder.geocoderStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tasks.yandexgeocoder.geocoderStructure.Response;


public class YandexGeocoderResponse {
    @JsonProperty("response")
    private Response response;
}
