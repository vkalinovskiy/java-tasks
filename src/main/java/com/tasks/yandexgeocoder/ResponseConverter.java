package com.tasks.yandexgeocoder;

import com.tasks.yandexgeocoder.responsestructure.Address;
import com.tasks.yandexgeocoder.responsestructure.GeocoderResponse;

public class ResponseConverter {
    public Location convert(GeocoderResponse response) {
        Address address = response.getAddress();

        return new Location(
            address.getCountryCode(),
            address.getCountry(),
            address.getProvince(),
            address.getCity(),
            address.getStreet(),
            address.getHouse(),
            address.getPostalCode()
        );
    }
}
