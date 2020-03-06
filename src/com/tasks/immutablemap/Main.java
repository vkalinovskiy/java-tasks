package com.tasks.immutablemap;

import java.util.Comparator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Comparator<String> comparator = new Comparator<>() {
            public int compare(String o1, String o2) {
                if (o1 == o2) {
                    return 0;
                }
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o1.compareTo(o2);
            }
        };

        var map = ImmutableMap.TreeMapBuilder.<String, Integer>fromTreeMap()
                .put("first", 1)
                .putAll(Map.of("second", 2, "third", 3))
                .withComparator(comparator)
                .put("aaa", 111)
                .build();

        System.out.println("Size of collection: " + map.size());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        map.put("fourth", 4);
    }
}
