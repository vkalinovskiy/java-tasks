package com.springusertrack.dao;

import com.springusertrack.model.Track;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TrackMapper {
    int insert(Track track);

    List<Track> getByUserId(int id);

    List<Track> selectByUserAndTimePeriod(@Param("userId") int userId,
                                          @Param("dateFrom") LocalDateTime dateFrom,
                                          @Param("dateTo") LocalDateTime dateTo);
}
