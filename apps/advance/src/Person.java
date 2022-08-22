package com.apps.advance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void execute() throws NoSuchMethodException, SecurityException {
        Person person = new Person("Wilson");
        Class cls = person.getClass();

        try {
            Method method = cls.getMethod("getName");
            System.out.println(method.invoke(person));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e);
        }
    }
}