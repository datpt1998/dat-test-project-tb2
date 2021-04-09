package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.Data.Animal;

@Configuration
public class AnimalConfig {
    @Bean
    public Animal springAnimal() {
        return new Animal("spring", 5);
    }
}
