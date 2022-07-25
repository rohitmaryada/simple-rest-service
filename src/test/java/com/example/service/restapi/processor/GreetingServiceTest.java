package com.example.service.restapi.processor;

import com.example.service.restapi.model.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class GreetingServiceTest {

  @InjectMocks
  GreetingService greetingService;

  private Greeting getMockGreeting() {
    return new Greeting(1, "TestMessage");
  }

  @Test
  public void getGreetingSuccess() {
    Greeting greeting = greetingService.getGreeting("Test");
    assertNotNull(greeting);
    assertEquals("Hello, Test!", greeting.getContent());
  }

  @Test
  public void getMessage() {
    Greeting greeting = greetingService.getMessage(getMockGreeting());
    assertNotNull(greeting);
    assertEquals("Hello, TestMessage!", greeting.getContent());
  }
}
