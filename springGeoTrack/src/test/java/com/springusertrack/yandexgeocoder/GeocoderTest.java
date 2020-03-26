package com.springusertrack.yandexgeocoder;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class GeocoderTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @BeforeEach
    void setUp() {
    }

    @Test
    void getLocation() {
//        stubFor(get("/json").willReturn(okJson("{ \"message\": \"Hello\" }")));

//
//        Result result = myHttpServiceCallingObject.doSomething();
//
//        assertTrue(result.wasSuccessful());
//
//        verify(postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
//                .withRequestBody(matching(".*<message>1234</message>.*"))
//                .withHeader("Content-Type", notMatching("application/json")));

    }
}