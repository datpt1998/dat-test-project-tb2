package com.example;

import com.example.config.CustomBanner;
import com.example.config.MysqlConnector;
import com.mysql.cj.protocol.Resultset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.Data.Animal;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.io.Console;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SpringBootApplication(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class RealMain implements CommandLineRunner {
    @Autowired
    private Animal theAnimal;
    @Autowired
    private MysqlConnector mysqlConnector;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RealMain.class);
        application.setBanner(new CustomBanner());
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Connection connection = mysqlConnector.getMySQLConnection();
        ResultSet resultset = connection.prepareStatement("SELECT * FROM accounts").executeQuery();
        while (resultset.next()) {
            System.out.println(resultset.getString("user_name"));
        }
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
