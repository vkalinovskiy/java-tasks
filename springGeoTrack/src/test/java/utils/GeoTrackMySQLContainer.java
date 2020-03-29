package utils;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class GeoTrackMySQLContainer extends PostgreSQLContainer<GeoTrackMySQLContainer> implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String IMAGE_VERSION = "postgres:10";

    private static GeoTrackMySQLContainer container = new GeoTrackMySQLContainer()
            .withDatabaseName("user_track_spring")
            .withUsername("root")
            .withPassword("root");

    private GeoTrackMySQLContainer() {
        super(IMAGE_VERSION);
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        container.start();
        System.setProperty("spring.datasource.jdbc-url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }
}

