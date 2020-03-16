package com.tasks.yandexgeocoder;

public class YandexGeocoder {
    protected Double lng;
    protected Double lat;
    protected String apiKey;
    protected final String geocodeUrl = "https://geocode-maps.yandex.ru/1.x/";

    public String getAddress() {
        try {
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setBaseUrl(geocodeUrl);

            String coordinatesString = lat + "," + lng;

            requestHandler.addGetParam("geocode", coordinatesString);
            requestHandler.addGetParam("apikey", apiKey);
            String stringResponse = requestHandler.sendRequest();

            ResponseHandler responseHandler = new ResponseHandler(stringResponse);

            String address = responseHandler.getAddress();

            return address;
        } catch (Exception exception) {
            throw new RuntimeException("Yandex api error");
        }
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

        public YandexGeocoder build() {
            return geocoder;
        }
    }
}

