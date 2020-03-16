package com.tasks.yandexgeocoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasks.yandexgeocoder.geocoderStructure.YandexGeocoderResponse;

import java.io.IOException;
import java.net.URL;

public class YandexGeocoder {
    protected Double lng;
    protected Double lat;
    protected String apiKey;
    protected String format;
    protected final String geocodeUrl = "https://geocode-maps.yandex.ru/1.x/";

    public Address getAddress() throws IOException {
        YandexGeocoderResponse response = getAddressFromApi();
        Address address = extractAddressFromResponse(response);

        return address;
    }

    protected URL buildRequestUrl() {
        try {
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setBaseUrl(geocodeUrl);
            String coordinatesString = lat + "," + lng;
            requestHandler.addGetParam("geocode", coordinatesString);
            requestHandler.addGetParam("apikey", apiKey);
            requestHandler.addGetParam("format", format);
            URL url = requestHandler.getRequestUrl();

            return url;
        } catch (Exception exception) {
            throw new RuntimeException("Error in the creation of url");
        }
    }

    protected YandexGeocoderResponse getAddressFromApi() throws IOException {
        URL url = buildRequestUrl();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        YandexGeocoderResponse response = objectMapper.readValue(url, YandexGeocoderResponse.class);

        return response;
    }

    protected Address extractAddressFromResponse(YandexGeocoderResponse response) {
        Address address = new Address();

        address.setArea("");
        address.setCountry("");
        address.setCountryCode("");
        address.setFormatted("");
        address.setLocality("");
        address.setPostalCode(1);
        address.setProvince("");

        return address;
    }

    public static class Builder {
        YandexGeocoder geocoder;

        Builder() {
            geocoder = new YandexGeocoder();
        }

        public Builder setLng(Double lng) {
            geocoder.lng = lng;

            return this;
        }

        public Builder setLat(Double lat) {
            geocoder.lat = lat;

            return this;
        }

        public Builder setApiKey(String apiKey) {
            geocoder.apiKey = apiKey;

            return this;
        }

        public Builder setFormat(String format) {
            geocoder.format = format;

            return this;
        }

        public YandexGeocoder build() {
            return geocoder;
        }
    }
}

