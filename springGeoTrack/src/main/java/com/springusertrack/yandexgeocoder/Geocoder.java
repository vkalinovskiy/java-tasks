package com.springusertrack.yandexgeocoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.springusertrack.yandexgeocoder.responsestructure.GeocoderResponse;
import com.springusertrack.yandexgeocoder.responsestructure.Location;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@RequiredArgsConstructor
public class Geocoder {
    protected String apiKey;
    protected ObjectMapper objectMapper;

    public Geocoder(@NonNull String apiKey) {
        this.apiKey = apiKey;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Location getLocation(@NonNull double lat, @NonNull double lon) {
        try {
            URL url = buildRequestUrl(lat, lon);

            return getLocationByUrl(url);
        } catch (Exception exception) {
            throw new RuntimeException("Error in request to Yandex API");
        }
    }

    protected URL buildRequestUrl(@NonNull double lat, @NonNull double lon) throws MalformedURLException {
        return new URL("https://geocode-maps.yandex.ru/1.x/?apikey="
                + apiKey + "&geocode=" + lat + "," + lon + "&results=1&format=json");
    }

    protected Location getLocationByUrl(URL requestUrl) throws IOException {
        GeocoderResponse response = objectMapper.readValue(requestUrl, GeocoderResponse.class);

        return response.getLocation();
    }
}

