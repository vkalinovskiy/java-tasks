package com.tasks.yandexgeocoder;

import java.io.IOException;

public class GeoMain {
    public static void main(String[] args) throws IOException {
        YandexGeocoder ya = new YandexGeocoder.Builder()
                .setLat(73.374437)
                .setLng(54.979878)
                .setApiKey("020ce9a8-6dbe-49fd-8705-1178fa1e2b47")
                .setFormat("json")
                .build();

        Address address = ya.getAddress();

        System.out.println(address.getCountry());
    }
}
