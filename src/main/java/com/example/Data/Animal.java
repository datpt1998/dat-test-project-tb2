package com.example.Data;

import java.util.Objects;

public class Animal {
    private Long id;
    private String name;
    private int age;

    public Animal(){}

    public Animal(Long id) {
        this.id = id;
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("name: %s, age: %d, id: %d", this.name, this.age, this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Animal) {
            Animal compareAnimal = (Animal) obj;
            return this.id == compareAnimal.id;
        }
        throw new IllegalArgumentException("Đm, đây éo phải animal");
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
