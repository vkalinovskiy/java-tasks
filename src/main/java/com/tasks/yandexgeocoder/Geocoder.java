package com.tasks.yandexgeocoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasks.yandexgeocoder.responsestructure.GeocoderResponse;

import java.io.IOException;
import java.net.URL;

public class Geocoder {
    protected String apiKey;
    protected String format;
    protected Integer results;
    protected final String apiUrl = "https://geocode-maps.yandex.ru/1.x/";

    protected URL buildRequestUrl(String coordinates) {
        try {
            ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder();
            apiRequestBuilder.setBaseUrl(apiUrl);
            apiRequestBuilder.addGetParam("geocode", coordinates);
            apiRequestBuilder.addGetParam("apikey", apiKey);
            apiRequestBuilder.addGetParam("results", results);
            apiRequestBuilder.addGetParam("format", "json");
            URL url = apiRequestBuilder.getRequestUrl();

            return url;
        } catch (Exception exception) {
            throw new RuntimeException("Error creation URL");
        }
    }

    protected GeocoderResponse getAddressFromApi(String coordinates) throws IOException {
        URL url = buildRequestUrl(coordinates);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GeocoderResponse response = objectMapper.readValue(url, GeocoderResponse.class);

        return response;
    }

    protected AddressFormatted getAddressFromCache() {
        return new AddressFormatted();
    }

    public AddressFormatted getAddress(Double lat, Double lng) throws IOException {
        String coordinates = lat + "," + lng;
        GeocoderResponse response = getAddressFromApi(coordinates);
        AddressFormatted addressFormatted = new GeocoderResponseConverter(response).convert();

        return addressFormatted;
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

        public Builder setResults(Integer results) {
            geocoder.results = results;

            return this;
        }

        public Geocoder build() {
            return geocoder;
        }
    }
}

