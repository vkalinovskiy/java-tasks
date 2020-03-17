package com.tasks.yandexgeocoder;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class CacheLocation {
    protected Cache<Coordinates, Location> cache;

    public CacheLocation() {
        createCache();
    }

    protected void createCache() {
        //settings for example
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    public void put(Coordinates coordinates, Location location) {
        cache.put(coordinates, location);
    }

    public Location get(Coordinates coordinates) {
        return cache.getIfPresent(coordinates);
    }
}
