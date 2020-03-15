package com.tasks.immutablemap;

import java.util.Comparator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        var map = ImmutableMap.TreeMapBuilder.<String, Integer>fromTreeMap()
                .put("first", 1)
                .putAll(Map.of("second", 2, "third", 3))
                .withComparator(Comparator.nullsFirst(String::compareTo))
                .put("aaa", 10)
                .build();

        System.out.println("Size of collection: " + map.size());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        map.put("fourth", 4);
    }
}
