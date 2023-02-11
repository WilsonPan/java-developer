package com.apps.advance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String s() {

    }

    public void execute() {
        Person person = new Person("Wilson");
        Class cls = person.getClass();

        try {
            Field field = cls.getDeclaredField("name");
            System.out.println("field " + field.get(person));
        } catch (NoSuchFieldException | SecurityException e) {
            System.out.println(e);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            System.out.println(e);
        }
    }
}