package com.springusertrack.controller;

import com.springusertrack.model.Track;
import com.springusertrack.service.track.TrackService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("tracks")
public class TrackController {
    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("")
    public Track create(@RequestBody Track track) {
        return trackService.insertWithLocation(track);
    }

    @GetMapping("/users/{id}")
    public List<Track> getByUser(@PathVariable int id,
                                 @RequestParam(required=false, name="date_from")
                                 @DateTimeFormat(pattern="yyyy-MM-dd") LocalDateTime dateFrom,
                                 @RequestParam(required=false, name="date_to")
                                 @DateTimeFormat(pattern="yyyy-MM-dd") LocalDateTime dateTo) {
        return trackService.selectByUserAndTimePeriod(id, dateFrom, dateTo);
    }
}
