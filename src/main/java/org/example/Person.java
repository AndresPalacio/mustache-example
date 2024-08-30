package org.example;

import java.util.HashMap;
import java.util.Map;

public class Person {

    public final String name;
    Map<String, Object> data = new HashMap<>();

    public Person (String name,Map<String, Object> data) {
        this.name = name;
        this.data = data;
    }
    public Person (String name, int age) {
        this.name = name;
        _age = age;
    }
    public int getAge () {
        return _age;
    }
    protected int _age;
}
