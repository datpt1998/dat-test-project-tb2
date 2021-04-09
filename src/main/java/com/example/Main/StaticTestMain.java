package com.example.Main;

import com.example.Data.Animal;
import com.example.Service.AnimalService;

public class StaticTestMain {
    public static void main(String[] args) {
        Animal animal =new Animal();
        AnimalService.becomeCow(animal);
        System.out.println(animal);
    }
}
