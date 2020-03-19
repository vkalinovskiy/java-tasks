package com.tasks.yandexgeocoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasks.yandexgeocoder.responsestructure.GeocoderResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.net.URL;

@RequiredArgsConstructor
@NonNull
public class Geocoder {
    protected String apiKey;
    protected CacheLocation cache;
    protected ObjectMapper objectMapper;

    public Geocoder(String apiKey) {
        this.apiKey = apiKey;
        cache = new CacheLocation();
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Location getLocation(Coordinates coordinates) {
        Location location = cache.get(coordinates);

        if (location == null) {
            location = getFromApi(coordinates);
            cache.put(coordinates, location);
        }

        return location;
    }

    protected Location getFromApi(Coordinates coordinates) {
        try {
            URL url = new URL("https://geocode-maps.yandex.ru/1.x/?apikey="
                    + apiKey + "&geocode=" + coordinates + "&results=1&format=json");

            GeocoderResponse response = objectMapper.readValue(url, GeocoderResponse.class);

            Location location = new ResponseConverter().convert(response);

            return location;
        } catch (Exception exception) {
            throw new RuntimeException("Error in request to Yandex API");
        }
    }
}

