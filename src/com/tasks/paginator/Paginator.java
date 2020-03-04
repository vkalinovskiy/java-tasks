package com.tasks.paginator;

import java.util.List;
import java.util.stream.Collectors;

public class Paginator<T> {
    protected int pageSize;
    protected List<T> elements;

    Paginator(List<T> elements, int pageSize) {
        this.elements = elements;

        if (pageSize < 1) {
            throw new IllegalArgumentException("pageSize can't be less than 1!");
        }

        this.pageSize = pageSize;
    }

    public List<T> getPage(int page) {
        if (page < 1) {
            throw new IllegalArgumentException("pageSize can't be less than 1!");
        }

        int fromIndex = this.pageSize * (page - 1);
        int toIndex = fromIndex + this.pageSize;

        return this.elements.subList(fromIndex, toIndex);
    }
}
