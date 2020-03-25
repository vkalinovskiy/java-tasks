package com.springusertrack.yandexgeocoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.springusertrack.yandexgeocoder.responsestructure.GeocoderResponse;
import com.springusertrack.yandexgeocoder.responsestructure.Location;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.net.URL;

@RequiredArgsConstructor
public class Geocoder {
    protected String apiKey;
    protected ObjectMapper objectMapper;

    public Geocoder(String apiKey) {
        this.apiKey = apiKey;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Location getLocation(@NonNull double lat, @NonNull double lon) {
        try {
            URL url = new URL("https://geocode-maps.yandex.ru/1.x/?apikey="
                    + apiKey + "&geocode=" + lat + "," + lon + "&results=1&format=json");

            GeocoderResponse response = objectMapper.readValue(url, GeocoderResponse.class);
            Location location = response.getLocation();

            return location;
        } catch (Exception exception) {
            throw new RuntimeException("Error in request to Yandex API");
        }
    }
}

