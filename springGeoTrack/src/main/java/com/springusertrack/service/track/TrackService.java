package com.springusertrack.service.track;

import com.springusertrack.dao.TrackLocationMapper;
import com.springusertrack.dao.TrackMapper;
import com.springusertrack.model.Track;
import com.springusertrack.model.TrackLocation;
import com.springusertrack.yandexgeocoder.Coordinates;
import com.springusertrack.yandexgeocoder.Geocoder;
import com.springusertrack.yandexgeocoder.Location;
import lombok.RequiredArgsConstructor;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackService {
    protected Geocoder geocoder;
    protected TrackMapper trackMapper;
    protected TrackLocationMapper trackLocationMapper;
    private static final Logger logger = LoggerFactory.getLogger(TrackService.class);

    @Autowired
    public TrackService(Geocoder geocoder, TrackMapper trackMapper, TrackLocationMapper trackLocationMapper) {
        this.geocoder = geocoder;
        this.trackMapper = trackMapper;
        this.trackLocationMapper = trackLocationMapper;
    }

    public Track insertWithLocation(Track track) {
        Coordinates coordinates = new Coordinates(track.getLat(), track.getLng());

        try {
            Location location = geocoder.getLocation(coordinates);
            TrackLocation trackLocation = new TrackLocation();

            trackLocation.setCountryCode(location.getCountryCode());
            trackLocation.setCountry(location.getCountry());
            trackLocation.setProvince(location.getProvince());
            trackLocation.setCity(location.getCity());
            trackLocation.setStreet(location.getStreet());
            trackLocation.setHouse(location.getHouse());
            trackLocation.setPostalCode(location.getPostalCode());

            int trackLocationId = trackLocationMapper.insert(trackLocation);
            track.setTrackLocationId(trackLocationId);
            track.setTrackLocation(trackLocation);
        } catch (Exception exception) {
            logger.error(() -> "Error receiving location from Yandex. Coordinates: " + coordinates);
            logger.error(() -> "Error message: " + exception.getMessage());
        }

        trackMapper.insert(track);

        return track;
    }

    public List<Track> selectByUserAndTimePeriod(int userId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return trackMapper.selectByUserAndTimePeriod(userId, dateFrom, dateTo);
    }


}
