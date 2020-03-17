package com.tasks.yandexgeocoder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GeocoderTest {

    @org.junit.jupiter.api.Test
    void addressesShouldEquals() throws IOException {
        Geocoder ya = new Geocoder.Builder()
                .setApiKey("020ce9a8-6dbe-49fd-8705-1178fa1e2b47")
                .build();

        Coordinates coordinates = new Coordinates(73.374437, 54.979878);

        Location address = ya.getAddress(coordinates);

        assertEquals("RU", address.getCountryCode());
        assertEquals(644024, address.getPostalCode());
        assertEquals("Россия", address.getCountry());
        assertEquals("Омская область", address.getProvince());
        assertEquals("Омск", address.getCity());
        assertEquals("Ильинская улица", address.getStreet());
        assertEquals("4", address.getHouse());
    }
}