package com.springusertrack.yandexgeocoder;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.springusertrack.yandexgeocoder.responsestructure.Location;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeocoderTest {
    String geocoderResponseMock;
    Geocoder geocoder;

    protected final double lat = 73.374437;
    protected final double lon = 54.979878;

    protected final String yandexGeocoderBaseURL = "https://geocode-maps.yandex.ru";
    protected final Integer wireMockPort = 8089;
    protected final String wireMockBaseUrl = "http://localhost:" + wireMockPort;


    @Rule
    public WireMockRule wiremock = new WireMockRule(wireMockPort);


    @Before
    @SneakyThrows
    public void setUp() {
        File file = new File("src/test/resources/yandexgeocoder/geocoder-response.json");
        geocoderResponseMock = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        geocoder = new Geocoder("020ce9a8-6dbe-49fd-8705-1178fa1e2b47");

        wiremock.start();
    }

    @After
    public void tearDown() {
        wiremock.stop();
    }

    @Test
    @SneakyThrows
    public void getLocation() {
        URL requestUrl = geocoder.buildRequestUrl(lat, lon);
        String urlForStub = requestUrl.toString().replaceAll(yandexGeocoderBaseURL, "");

        stubFor(get(urlEqualTo(urlForStub))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(geocoderResponseMock))
        );

        String urlForMockRequest = requestUrl.toString()
                .replaceAll(yandexGeocoderBaseURL, wireMockBaseUrl);

        Location location = geocoder.getLocationByUrl(new URL(urlForMockRequest));

        assertEquals("RU1", location.getCountryCode());
        assertEquals(644024, location.getPostalCode());
        assertEquals("Россия", location.getCountry());
        assertEquals("Омская область", location.getProvince());
        assertEquals("Омск", location.getCity());
        assertEquals("Ильинская улица", location.getStreet());
        assertEquals("4", location.getHouse());
    }
}