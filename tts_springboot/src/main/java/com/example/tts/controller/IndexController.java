package com.example.tts.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "<h1>Welcome to the Text to Speech  application.</h1>";
    }
}
