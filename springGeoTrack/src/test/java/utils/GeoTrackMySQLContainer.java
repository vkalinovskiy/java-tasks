package utils;

import org.testcontainers.containers.MySQLContainer;

public class GeoTrackMySQLContainer extends MySQLContainer<GeoTrackMySQLContainer> {

    private static final String IMAGE_VERSION = "mysql:5.8";

    private static GeoTrackMySQLContainer container;

    private GeoTrackMySQLContainer() {
        super(IMAGE_VERSION);
    }

    public static GeoTrackMySQLContainer getInstance() {
        if (container == null) {
            container = new GeoTrackMySQLContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
