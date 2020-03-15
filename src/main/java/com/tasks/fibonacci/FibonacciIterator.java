package com.tasks.fibonacci;

import java.util.Iterator;

public class FibonacciIterator implements Iterator<Integer> {

    private int current = 1;
    private int prev = 0;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int value = prev + current;
        prev = current;
        current = value;

        return value;
    }
}
