package com.tasks.yandexgeocoder;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Coordinates {
    public final double latitude;
    public final double longitude;

    public Coordinates(double lat, double lon) {
        latitude = lat;
        longitude = lon;
    }

    public String getCacheKey() {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(latitude) + "," + df.format(longitude);
    }

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}
