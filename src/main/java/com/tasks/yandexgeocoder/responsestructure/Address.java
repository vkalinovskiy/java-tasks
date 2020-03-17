package com.tasks.yandexgeocoder.responsestructure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Address {
    @JsonProperty("Components")
    private List<ComponentsItem> components;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("postal_code")
    private Integer postalCode;
    private Map<String, String> details;

    @JsonCreator
    public Address(@JsonProperty("Components") List<ComponentsItem> components) {
        details = components.stream()
                .collect(Collectors.toMap(ComponentsItem::getKind, ComponentsItem::getName, (name1, name2) -> name2));
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return details.get("country");
    }

    public String getProvince() {
        return details.get("province");
    }

    public String getCity() {
        return details.get("locality");
    }

    public String getStreet() {
        return details.get("street");
    }

    public String getHouse() {
        return details.get("house");
    }
}
