package com.springusertrack.service.geolocation;

import com.springusertrack.model.Track;
import com.springusertrack.model.TrackLocation;
import com.springusertrack.yandexgeocoder.Geocoder;
import com.springusertrack.yandexgeocoder.responsestructure.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YandexGeolocation implements GeolocationInterface {
    protected Geocoder geocoder;

    @Autowired
    public YandexGeolocation(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    @Override
    public TrackLocation getTrackLocation(Track track) {
        Location location = geocoder.getLocation(track.getLat(), track.getLng());
        TrackLocation trackLocation = convert(location);

        return trackLocation;
    }
}
