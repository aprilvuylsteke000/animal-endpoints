package io.github.aprilvuylsteke000.animalendpoints.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to April's Image Spring Boot Application!";
    }
}
