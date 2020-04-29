package com.javanorth.spring.springboot_swagger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SwaggerController {

    @RequestMapping(value = "/test", method = {RequestMethod.GET,RequestMethod.POST})
    public String test() {
        return "Hello World, Swagger!";
    }

    @RequestMapping(value = "/testParam", method = {RequestMethod.GET,RequestMethod.POST})
    public String testWithParameters(@RequestParam String param) {
        return param;
    }
}
