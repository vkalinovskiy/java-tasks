package com.tasks.yandexgeocoder;

public class Location {
    protected String countryCode;
    protected String country;
    protected String province;
    protected String city;
    protected String street;
    protected String house;
    protected Integer postalCode;

    public Location() {

    }

    public Location(String countryCode, String country, String province,
                    String city, String street, String house, Integer postalCode) {
        this.countryCode = countryCode;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.house = house;
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
