package com.tasks.yandexgeocoder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Coordinates)) {
            return false;
        }

        Coordinates that = (Coordinates) o;
        return Double.compare(that.latitude, latitude) == 0
                && Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);

        return Objects.hash(df.format(latitude), df.format(longitude));
    }
}
