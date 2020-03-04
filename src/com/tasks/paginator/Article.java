package com.tasks.paginator;

public class Article {
    String title;
    String annotation;

    Article(String title, String annotation) {
        this.title = title;
        this.annotation = annotation;
    }

    public String getTitle() {
        return title;
    }

    public String getAnnotation() {
        return annotation;
    }
}
