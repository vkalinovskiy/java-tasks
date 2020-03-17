package com.tasks.yandexgeocoder;

import com.tasks.yandexgeocoder.responsestructure.Address;
import com.tasks.yandexgeocoder.responsestructure.GeocoderResponse;

public class GeocoderResponseConverter {
    protected GeocoderResponse geocoderResponse;
    protected AddressFormatted addressFormatted;
    protected String countryCode;
    protected Integer postalCode;
    protected String country;
    protected String province;
    protected String city;
    protected String street;
    protected String house;

    public GeocoderResponseConverter(GeocoderResponse geocoderResponse) {
        this.geocoderResponse = geocoderResponse;
    }

    public void setGeocoderResponse(GeocoderResponse geocoderResponse) {
        this.geocoderResponse = geocoderResponse;
    }

    public AddressFormatted convert() {
        extractDataFromResponse();
        setDataToAddressFormatted();

        return addressFormatted;
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
        addressFormatted = new AddressFormatted();

        addressFormatted.setCountryCode(countryCode);
        addressFormatted.setPostalCode(postalCode);
        addressFormatted.setCountry(country);
        addressFormatted.setProvince(province);
        addressFormatted.setCity(city);
        addressFormatted.setStreet(street);
        addressFormatted.setHouse(house);
    }
}
