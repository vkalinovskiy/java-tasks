package com.tasks.yandexgeocoder;

import com.tasks.yandexgeocoder.responsestructure.Address;
import com.tasks.yandexgeocoder.responsestructure.GeocoderResponse;

public class ResponseConverter {
    protected GeocoderResponse geocoderResponse;
    protected Location location;
    protected String countryCode;
    protected Integer postalCode;
    protected String country;
    protected String province;
    protected String city;
    protected String street;
    protected String house;

    public ResponseConverter(GeocoderResponse geocoderResponse) {
        this.geocoderResponse = geocoderResponse;
    }

    public void setResponse(GeocoderResponse geocoderResponse) {
        this.geocoderResponse = geocoderResponse;
    }

    public Location convert() {
        extractDataFromResponse();
        setDataToAddressFormatted();

        return location;
    }

    protected void extractDataFromResponse() {
        Address address = geocoderResponse.getAddress();

        countryCode = address.getCountryCode();
        postalCode = address.getPostalCode();
        country = address.getCountry();
        province = address.getProvince();
        city = address.getCity();
        street = address.getStreet();
        house = address.getHouse();
    }

    protected void setDataToAddressFormatted() {
        location = new Location();

        location.setCountryCode(countryCode);
        location.setPostalCode(postalCode);
        location.setCountry(country);
        location.setProvince(province);
        location.setCity(city);
        location.setStreet(street);
        location.setHouse(house);
    }
}
