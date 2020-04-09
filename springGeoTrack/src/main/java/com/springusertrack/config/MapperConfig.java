package com.springusertrack.config;

import com.springusertrack.dao.TrackLocationMapper;
import com.springusertrack.dao.TrackMapper;
import com.springusertrack.dao.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    public MapperConfig(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Bean
    public MapperFactoryBean<UserMapper> userMapper() throws Exception {
        MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<TrackMapper> trackMapper() throws Exception {
        MapperFactoryBean<TrackMapper> factoryBean = new MapperFactoryBean<>(TrackMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<TrackLocationMapper> trackLocationMapper() throws Exception {
        MapperFactoryBean<TrackLocationMapper> factoryBean = new MapperFactoryBean<>(TrackLocationMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }
}
