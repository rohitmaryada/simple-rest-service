package com.example.service.restapi.processor;

import com.example.service.restapi.model.Greeting;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class GreetingService {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();
  private final AtomicLong postCounter = new AtomicLong();

  public Greeting getGreeting(String name){
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

  public Greeting getMessage(Greeting request){
    return new Greeting(postCounter.incrementAndGet(), String.format(template, request.getContent()));
  }
}
