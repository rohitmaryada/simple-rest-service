package com.example.service.restapi.controller;

import com.example.service.restapi.model.Greeting;
import org.springframework.web.bind.annotation.*;
import com.example.service.restapi.processor.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private final GreetingService greetingService;

  public GreetingController(GreetingService greetingService){
    this.greetingService = greetingService;
  }

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return greetingService.getGreeting(name);
  }

  @PostMapping
  public Greeting getMessage(@RequestBody Greeting request) {
    return greetingService.getMessage(request);
  }
}
