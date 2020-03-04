package com.tasks.immutablemap;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        ImmutableMap<String, Integer> map = ImmutableMap.Builder.fromHashMap<>()
        ImmutableMap<String, Integer> map = ImmutableMap.Builder.fromHashMap()
                .put("first", 1)
                .putAll(Map.of("second", 2, "third", 3))
                .build();

        System.out.println("Size of collection: " + map.size());

        map.put("fourth", 4);
    }
}
