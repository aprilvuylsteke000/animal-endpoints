package io.github.aprilvuylsteke000.animalendpoints;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLogger implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Override
    public void run(String... args) {
        System.out.println("Connected to database: " + datasourceUrl);
    }
}
