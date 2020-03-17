package com.tasks.yandexgeocoder;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class GeocoderCache {
    protected LoadingCache<String, String> cache;
    protected Geocoder geocoder;

    public GeocoderCache(Geocoder geocoder) {
        this.geocoder = geocoder;
        createCache();
    }

    protected void createCache() {
//        cache = new CacheLoader<String, AddressFormatted>() {
//            @Override
//            public AddressFormatted load(String key) throws Exception {
//                return cache.load(key);
//            }
//        };

        CacheLoader<String, AddressFormatted> loader;
        loader = new CacheLoader<>() {
            @Override
            public AddressFormatted load(String coordinates) {
                return geocoder.getAddress(coordinates);
            }
        };


        //settings for example
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(loader);


    }
}
