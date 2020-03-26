package com.springusertrack.controller;

import com.springusertrack.service.track.TrackService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {UserControllerTest.Initializer.class})
@SpringBootTest
public class UserControllerTest {
    @Autowired
    TrackService trackService;

    @ClassRule
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7.29")
            .withDatabaseName("user_track_spring")
            .withUsername("root")
            .withPassword("root");

    @Before
    public void setUp() {

    }

    @Test
    public void index() {
        List<?> tracks = trackService.selectByUserAndTimePeriod(1, null, null);

        assertEquals(1, tracks.size());
    }

    @Test
    public void get() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }


    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues
                    .of("spring.datasource.url=" + mysql.getJdbcUrl(),
                            "spring.datasource.username=" + mysql.getUsername(),
                            "spring.datasource.password=" + mysql.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }

    }
}