package com.tasks.paginator;

import java.util.List;
import java.util.stream.Collectors;

public class Paginator<T> {
    protected int pageSIze;
    protected List<T> elements;

    Paginator(List<T> elements, int pageSIze) {
        this.elements = elements;

        if (pageSIze < 1) {
            throw new IllegalArgumentException("pageSIze can't be less than 1!");
        }

        this.pageSIze = pageSIze;
    }

    public List<T> getPage(int page) {
        if (page < 1) {
            throw new IllegalArgumentException("pageSIze can't be less than 1!");
        }

        int fromIndex = this.pageSIze * (page - 1);
        int toIndex = fromIndex + this.pageSIze;

        return this.elements.subList(fromIndex, toIndex);
    }
}
