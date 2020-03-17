package com.tasks.yandexgeocoder;

public class Coordinates {
    public final double latitude;
    public final double longitude;

    public Coordinates(double lat, double lon) {
        latitude = lat;
        longitude = lon;
    }

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}
