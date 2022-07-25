package com.example.service.restapi.controller;

import com.example.service.restapi.model.Greeting;
import com.example.service.restapi.processor.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
@AutoConfigureMockMvc
public class GreetingControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  GreetingService mockGreetingService;

  private static final String URL_RESOURCE = "/greeting";
  private static final String X_MW_WS_CALLERID = "X_MW_WS_CallerId";
  private static final String CALLER_ID = "Junit";

  private Greeting getMockGreeting() {
    return new Greeting(1, "Test");
  }

  private String buildGreetingJSON() {
    return "{" +
        "\"id\":\"1\"," +
        "\"content\":\"Test\"}";
  }

  @Test
  public void testGetGreeting() throws Exception {
    when(mockGreetingService.getGreeting(anyString())).thenReturn(getMockGreeting());
    MvcResult mvcResult = mockMvc.perform(get(URL_RESOURCE)
            .header(X_MW_WS_CALLERID, CALLER_ID))
        .andExpect(status().is2xxSuccessful()).andReturn();

    assertEquals("{\"id\":1,\"content\":\"Test\"}", mvcResult.getResponse().getContentAsString());
  }

  @Test
  public void testGetMessage() throws Exception {
    ResultMatcher ok = MockMvcResultMatchers.status().isOk();
    String greetingJSON = buildGreetingJSON();
    byte[] contentBytes = greetingJSON.getBytes();
    when(mockGreetingService.getMessage(any())).thenReturn(getMockGreeting());
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/")
        .content(contentBytes)
        .contentType("application/JSON");
    MvcResult result = mockMvc.perform(builder).andExpect(ok).andReturn();

    assertEquals("{\"id\":1,\"content\":\"Test\"}", result.getResponse().getContentAsString());
  }

}
