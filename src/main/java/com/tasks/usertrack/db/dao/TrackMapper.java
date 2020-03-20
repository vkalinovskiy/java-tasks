package com.tasks.usertrack.db.dao;

import com.tasks.usertrack.db.models.Track;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TrackMapper {
    int insert(Track track);

    List<Track> getByUserId(int id);

    List<Track> selectByUserAndTimePeriod(@Param("userId") int userId, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
}
