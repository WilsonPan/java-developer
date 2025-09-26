package com.example.mystarter;

public class MyService {
    private final String prefix;
    private final String suffix;

    public MyService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String message) {
        return prefix + " " + message + " " + suffix;
    }
}
