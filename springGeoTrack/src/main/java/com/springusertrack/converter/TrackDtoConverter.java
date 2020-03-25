package com.springusertrack.converter;

import com.springusertrack.dto.TrackDto;
import com.springusertrack.model.Track;
import com.springusertrack.model.TrackLocation;
import org.springframework.stereotype.Component;

@Component
public class TrackDtoConverter implements DtoConverter<TrackDto, Track> {
    @Override
    public Track convertToEntity(TrackDto dto) {
        Track track = new Track();
        track.setLat(dto.getLat());
        track.setLng(dto.getLng());
        track.setUserId(dto.getUserId());

        return track;
    }

    @Override
    public TrackDto convertToDto(Track track) {
        TrackDto dto = new TrackDto();
        dto.setLat(track.getLat());
        dto.setLng(track.getLng());
        dto.setUserId(track.getUserId());

        TrackLocation trackLocation = track.getTrackLocation();

        if(trackLocation != null) {
            dto.setPostalCode(trackLocation.getPostalCode());
            dto.setCountryCode(trackLocation.getCountryCode());
            dto.setCountry(trackLocation.getCountry());
            dto.setProvince(trackLocation.getProvince());
            dto.setCity(trackLocation.getCity());
            dto.setStreet(trackLocation.getStreet());
            dto.setHouse(trackLocation.getHouse());
        }

        return dto;
    }
}
