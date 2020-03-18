package com.tasks.yandexgeocoder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeocoderTest {

    @Test
    void addressesShouldEquals() {
        Geocoder ya = new Geocoder.Builder()
                .setApiKey("020ce9a8-6dbe-49fd-8705-1178fa1e2b47")
                .build();

        Coordinates coordinates = new Coordinates(73.374437, 54.979878);

        Location location = ya.getLocation(coordinates);

        assertEquals("RU", location.getCountryCode());
        assertEquals(644024, location.getPostalCode());
        assertEquals("Россия", location.getCountry());
        assertEquals("Омская область", location.getProvince());
        assertEquals("Омск", location.getCity());
        assertEquals("Ильинская улица", location.getStreet());
        assertEquals("4", location.getHouse());

        coordinates = new Coordinates(73.374411, 54.979811);

        location = ya.getLocation(coordinates);

        assertEquals("RU", location.getCountryCode());
        assertEquals(644024, location.getPostalCode());
        assertEquals("Россия", location.getCountry());
        assertEquals("Омская область", location.getProvince());
        assertEquals("Омск", location.getCity());
        assertEquals("Ильинская улица", location.getStreet());
        assertEquals("4", location.getHouse());
    }
}