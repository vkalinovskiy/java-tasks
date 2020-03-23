package com.springusertrack.dao;

import com.springusertrack.model.TrackLocation;

public interface TrackLocationMapper {
    TrackLocation getById(Integer id);

    int insert(TrackLocation trackLocation);
}
