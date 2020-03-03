package com.tasks.paginator;

import java.util.List;
import java.util.stream.Collectors;

public class Paginator<T> {
    protected int countOfPage;
    protected List<T> elements;

    Paginator(List<T> elements, int countOfPage) {
        this.elements = elements;

        if (countOfPage < 1) {
            throw new IllegalArgumentException("countOfPage can't be less than 1!");
        }

        this.countOfPage = countOfPage;
    }

    public List<T> getPage(int page) {

        if (page < 1) {
            throw new IllegalArgumentException("countOfPage can't be less than 1!");
        }

        page--;

        int offset = this.countOfPage * page;

        return this.elements
                .stream()
                .skip(offset)
                .limit(this.countOfPage)
                .collect(Collectors.toList());
    }
}
