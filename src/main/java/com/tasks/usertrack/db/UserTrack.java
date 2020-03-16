package com.tasks.usertrack.db;

import com.tasks.usertrack.db.dao.TrackMapper;
import com.tasks.usertrack.db.models.Track;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserTrack {
    public static void main(String[] args) {
        try (Reader reader = Resources.getResourceAsReader("user_track/mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();

            TrackMapper trackMapper = session.getMapper(TrackMapper.class);

            Date dateFrom = new Date(120, Calendar.MARCH, 13);
            Date dateTo = new Date(120, Calendar.MARCH, 16);

            List<Track> tracks = trackMapper.selectByUserAndTimePeriod(1, dateFrom, dateTo);

            System.out.println(tracks.size());

            session.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
