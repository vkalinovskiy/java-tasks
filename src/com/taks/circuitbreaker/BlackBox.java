package com.taks.circuitbreaker;

public class BlackBox {
    public void execute() throws RuntimeException {
        throw new RuntimeException("Random exception");
    }
}
