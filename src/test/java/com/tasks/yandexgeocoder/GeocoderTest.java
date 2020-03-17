package com.tasks.yandexgeocoder;

import static org.junit.jupiter.api.Assertions.*;

class GeocoderTest {

    @org.junit.jupiter.api.Test
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
    }
}