package com.example.objspringrestdatajpa.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${app.envexample}")
    private String message;
    @GetMapping(path = "/helloworld")
    public String helloWorld(){
        return message;
    }
    @GetMapping(path = "/html")
    public String getHtml(){
        return "<!doctype> html"+
                "<html>"+
                "<body>" +
                "<h1>Hola mundo</h1>" +
                "</body>"+
                "</html>";
    }
}
