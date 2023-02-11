package com.apps;

public class Outer {
    private string name;
    private static int radius = 1;

    public void execute() {
        new Service() {
            public void method() {

            }
        }.method();
    }

    public static class StaticInner {
        public int getRedius() {
            return radius;
        }
    }
}

public interface Service {
    void method();
}
