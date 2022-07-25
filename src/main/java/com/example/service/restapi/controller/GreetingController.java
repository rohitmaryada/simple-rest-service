package com.example.service.restapi.controller;

import com.example.service.restapi.model.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();
  private final AtomicLong postCounter = new AtomicLong();

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

  @PostMapping
  public Greeting getMessage(@RequestBody Greeting request) {
    return new Greeting(postCounter.incrementAndGet(), String.format(template, request.getContent()));
  }
}
