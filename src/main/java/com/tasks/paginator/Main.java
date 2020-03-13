package com.tasks.paginator;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Article> articleList = Arrays.asList(
                new Article("title 1", "annotation 1"),
                new Article("title 2", "annotation 2"),
                new Article("title 3", "annotation 3"),
                new Article("title 4", "annotation 4"),
                new Article("title 5", "annotation 5"),
                new Article("title 6", "annotation 6"),
                new Article("title 7", "annotation 7"),
                new Article("title 8", "annotation 8"),
                new Article("title 9", "annotation 9"),
                new Article("title 10", "annotation 10"),
                new Article("title 11", "annotation 11"),
                new Article("title 12", "annotation 12")
        );

        Paginator<Article> paginator = new Paginator<Article>(articleList, 2);

        paginator.getPage(2).forEach(article -> {
            System.out.println("Title: " + article.getTitle());
            System.out.println("Annotation: " + article.getAnnotation());
            System.out.println("------------------");
        });
    }
}
