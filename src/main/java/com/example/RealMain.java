package com.example;

import com.example.config.CustomBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.Data.Animal;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RealMain implements CommandLineRunner {
    @Autowired
    private Animal theAnimal;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RealMain.class);
        application.setBanner(new CustomBanner());
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(theAnimal.getName());
        List<Long> testLongs = new ArrayList<>();
        testLongs.add(4L);
        testLongs.add(5L);
        testLongs.add(6L);
        Long test = new Long(4L);
        System.out.println(testLongs.contains(test));
        System.out.println(test == testLongs.get(0));
        System.out.println(test.equals(testLongs.get(0)));
    }
}
