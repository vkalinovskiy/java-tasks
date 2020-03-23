package com.springusertrack.model;

import java.util.Date;

public class Track {
    private Integer id;
    private double lat;
    private double lng;
    private Date createdAt;
    private Integer userId;
    private Integer trackLocationId;
    private TrackLocation trackLocation;

    public Integer getTrackLocationId() {
        return trackLocationId;
    }

    public void setTrackLocationId(Integer trackLocationId) {
        this.trackLocationId = trackLocationId;
    }

    public TrackLocation getTrackLocation() {
        return trackLocation;
    }

    public void setTrackLocation(TrackLocation trackLocation) {
        this.trackLocation = trackLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
