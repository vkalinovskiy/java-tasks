package com.springusertrack.service.geolocation;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.springusertrack.model.Track;
import com.springusertrack.model.TrackLocation;
import com.springusertrack.yandexgeocoder.Geocoder;
import com.springusertrack.yandexgeocoder.responsestructure.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

@Service("cachedGeolocationService")
public class CachedGeolocationService implements GeolocationInterface {
    protected CacheLocation cache;
    protected Geocoder geocoder;

    @Autowired
    public CachedGeolocationService(Geocoder geocoder) {
        this.geocoder = geocoder;
        cache = new CacheLocation();
    }

    @Override
    public TrackLocation getTrackLocation(Track track) {
        TrackLocation trackLocation = cache.get(track);

        if (trackLocation == null) {
            trackLocation = getFromApi(track);
            cache.put(track, trackLocation);
        }

        return trackLocation;
    }

    protected TrackLocation getFromApi(Track track) {
        Location location = geocoder.getLocation(track.getLat(), track.getLng());
        TrackLocation trackLocation = convert(location);

        return trackLocation;
    }

    protected class CacheLocation {
        protected Cache<String, TrackLocation> cache;

        public CacheLocation() {
            createCache();
        }

        protected void createCache() {
            //settings for example
            cache = CacheBuilder.newBuilder()
                    .expireAfterAccess(10, TimeUnit.MINUTES)
                    .maximumSize(100)
                    .build();
        }

        public void put(Track track, TrackLocation trackLocation) {
            cache.put(getCacheKey(track), trackLocation);
        }

        public TrackLocation get(Track track) {
            return cache.getIfPresent(getCacheKey(track));
        }

        protected String getCacheKey(Track track) {
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);

            return df.format(track.getLat()) + "," + df.format(track.getLng());
        }
    }

}




