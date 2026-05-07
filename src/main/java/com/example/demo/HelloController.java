package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
            System.out.println("Hello API called");
        return "Hello";
    }

    @GetMapping("/hi")
    public String sayHi() {
            System.out.println("Hi API called");
        return "Hi";
    }
}
