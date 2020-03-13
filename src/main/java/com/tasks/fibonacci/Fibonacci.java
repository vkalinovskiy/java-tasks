package com.tasks.fibonacci;

public class Fibonacci {
    public static void main(String[] args) {
        FibonacciIterator iterator = new FibonacciIterator();

        while(iterator.hasNext()){
            int number = iterator.next();
            System.out.println(number);

            if (number > 100) break;
        }
    }
}

