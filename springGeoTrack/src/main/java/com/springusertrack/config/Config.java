package com.springusertrack.config;

import com.springusertrack.dao.TrackLocationMapper;
import com.springusertrack.dao.TrackMapper;
import com.springusertrack.dao.UserMapper;
import com.springusertrack.yandexgeocoder.Geocoder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Value("${yandex.apikey}")
    private String yandexApiKey;

    @Bean
    public Geocoder geocoder() throws Exception {
        return new Geocoder(yandexApiKey);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
    }

    @Bean
    public MapperFactoryBean<UserMapper> userMapper() throws Exception {
        MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory());
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<TrackMapper> trackMapper() throws Exception {
        MapperFactoryBean<TrackMapper> factoryBean = new MapperFactoryBean<>(TrackMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory());
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<TrackLocationMapper> trackLocationMapper() throws Exception {
        MapperFactoryBean<TrackLocationMapper> factoryBean = new MapperFactoryBean<>(TrackLocationMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory());
        return factoryBean;
    }
}

