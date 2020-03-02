package com.tasks.wordcount;

import java.io.File;
import java.util.Map;

public class WordCountMain {
    public static void main(String[] args) {
        File resourceFile = new File("src/com/tasks/wordcount/storage/text.txt");
        String filePath = resourceFile.getAbsolutePath();

        WordCounter counter = new WordCounter();
        Map<String, Integer> result = counter.wordCount(filePath);

        result.forEach((k, v) -> System.out.println(k + " - " + v));
    }
}
