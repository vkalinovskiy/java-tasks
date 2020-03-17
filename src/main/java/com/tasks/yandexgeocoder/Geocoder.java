package com.tasks.yandexgeocoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasks.yandexgeocoder.responsestructure.GeocoderResponse;

import java.net.URL;

public class Geocoder {
    protected String apiKey;
    protected CacheLocation cache;

    public Geocoder() {
        cache = new CacheLocation();
    }

    public Location getLocation(Coordinates coordinates) {
        Location location;

        location = getFromCache(coordinates);

        if (location == null) {
            location = getFromApi(coordinates);
        }

        return location;
    }

    protected Location getFromApi(Coordinates coordinates) {
        try {
            URL url = new URL("https://geocode-maps.yandex.ru/1.x/?apikey="
                    + apiKey + "&geocode=" + coordinates + "&results=1&format=json");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            GeocoderResponse response = objectMapper.readValue(url, GeocoderResponse.class);

            Location location = new ResponseConverter(response).convert();

            cache.put(coordinates, location);

            return location;
        } catch (Exception exception) {
            throw new RuntimeException("Error in request to Yandex API");
        }

    }

    protected Location getFromCache(Coordinates coordinates) {
        return cache.get(coordinates);
    }

    public static class Builder {
        Geocoder geocoder;

        Builder() {
            geocoder = new Geocoder();
        }

        public Builder setApiKey(String apiKey) {
            geocoder.apiKey = apiKey;

            return this;
        }

        public Geocoder build() {
            return geocoder;
        }
    }
}

