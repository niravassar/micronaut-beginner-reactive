package com.reactive.beginner.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller()
public class MovieController {

    @Get("/hello")
    String sayHello() {
        return "Hello";
    }
}
