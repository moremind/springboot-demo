package com.javanorth.spring.springboot_swagger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SwaggerController {

    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }
}
