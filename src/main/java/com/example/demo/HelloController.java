package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/hi")
    public String sayHi() {
        return "Hi";
    }
    @GetMapping("/health")
    public String healthCheck() {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formatter =
                java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

        return "I am fine :D Current time: " + now.format(formatter);
    }
}