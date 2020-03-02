package com.tasks.wordcount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {
    public Map<String, Integer> wordCount(String filePath) {
        Map<String, Integer> wordToCount = new HashMap<String, Integer>();
        Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            wordToCount = stream
                    .filter(line -> !line.isEmpty())
                    .map(String::toLowerCase)
                    .flatMap(line -> pattern.splitAsStream(line))
                    .collect(Collectors.groupingBy(i -> i, Collectors.summingInt(x -> 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordToCount;
    }
}
