package com.springusertrack.service.geolocation;

import com.springusertrack.model.Track;
import com.springusertrack.model.TrackLocation;
import com.springusertrack.yandexgeocoder.responsestructure.Location;

public interface GeolocationInterface {
    TrackLocation getTrackLocation(Track track);

    default TrackLocation convert(Location location) {
        TrackLocation trackLocation = new TrackLocation();

        trackLocation.setCountryCode(location.getCountryCode());
        trackLocation.setCountry(location.getCountry());
        trackLocation.setProvince(location.getProvince());
        trackLocation.setCity(location.getCity());
        trackLocation.setStreet(location.getStreet());
        trackLocation.setHouse(location.getHouse());
        trackLocation.setPostalCode(location.getPostalCode());

        return trackLocation;
    }
}
