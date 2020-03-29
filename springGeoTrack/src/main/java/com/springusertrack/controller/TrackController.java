package com.springusertrack.controller;

import com.springusertrack.converter.TrackDtoConverter;
import com.springusertrack.dto.TrackDto;
import com.springusertrack.model.Track;
import com.springusertrack.service.track.TrackService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("tracks")
public class TrackController {
    private final TrackService trackService;
    private final TrackDtoConverter trackDtoConverter;

    public TrackController(TrackService trackService, TrackDtoConverter trackDtoConverter) {
        this.trackService = trackService;
        this.trackDtoConverter = trackDtoConverter;
    }

    @PostMapping
    public TrackDto create(@Validated @RequestBody TrackDto dto) {
        Track track = trackDtoConverter.convertToEntity(dto);
        track = trackService.insertWithLocation(track);

        return trackDtoConverter.convertToDto(track);
    }

    @GetMapping
    public List<TrackDto> getByUser(@RequestParam(name="user_id") int user_id,
                                 @RequestParam(required=false, name="date_from")
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                                 @RequestParam(required=false, name="date_to")
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo) {
        List<Track> trackList = trackService.selectByUserAndTimePeriod(user_id, dateFrom, dateTo);
        return trackDtoConverter.convertToDtoList(trackList);
    }
}
