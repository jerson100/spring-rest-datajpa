package com.example.objspringrestdatajpa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path = "/helloworld")
    public String helloWorld(){
        return "Hello World replicando.";
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
