package com.springusertrack.service.track;

import com.springusertrack.dao.TrackLocationMapper;
import com.springusertrack.dao.TrackMapper;
import com.springusertrack.model.Track;
import com.springusertrack.model.TrackLocation;
import com.springusertrack.service.geolocation.GeolocationInterface;
import com.springusertrack.service.geolocation.YandexGeolocationService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackService {
    @Qualifier("yandexGeolocationService")
    protected GeolocationInterface geolocation;
    protected TrackMapper trackMapper;
    protected TrackLocationMapper trackLocationMapper;
    private static final Logger logger = LoggerFactory.getLogger(TrackService.class);

    @Autowired
    public TrackService(YandexGeolocationService geolocation, TrackMapper trackMapper, TrackLocationMapper trackLocationMapper) {
        this.geolocation = geolocation;
        this.trackMapper = trackMapper;
        this.trackLocationMapper = trackLocationMapper;
    }

    public Track insertWithLocation(Track track) {
        try {
            TrackLocation trackLocation = geolocation.getTrackLocation(track);

            int trackLocationId = trackLocationMapper.insert(trackLocation);
            track.setTrackLocationId(trackLocationId);
            track.setTrackLocation(trackLocation);
        } catch (Exception exception) {
            logger.error(() -> "Error receiving location from Yandex. Coordinates: " + track.getLat() + ", " + track.getLng());
            logger.error(() -> "Error message: " + exception.getMessage());
        }

        trackMapper.insert(track);

        return track;
    }

    public List<Track> selectByUserAndTimePeriod(int userId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return trackMapper.selectByUserAndTimePeriod(userId, dateFrom, dateTo);
    }
}
