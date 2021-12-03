package com.latitude.genoapay.codingchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.latitude.genoapay.codingchallenge.controller.dto.MaxStockProfitRequest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CodingChallengeApplicationTests {

  static ObjectMapper objectMapper;

  @Autowired
  private MockMvc mvc;

  @BeforeAll
  public static void setup() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  public void testNoStartDateTime() throws Exception {
    MaxStockProfitRequest request = new MaxStockProfitRequest(1L, null, LocalDateTime.of(2021, 12, 1, 11, 0, 0),
        Arrays.asList(1, 2, 3, 4, 5, 6));

    mvc.perform(post("/api/stock/getMaxProfit")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", CoreMatchers.is("startDateTime must be provided")));
  }

  @Test
  public void testNoStockPrices() throws Exception {
    MaxStockProfitRequest request = new MaxStockProfitRequest(1L, LocalDateTime.of(2021, 12, 1, 10, 0, 0), LocalDateTime.of(2021, 12, 1, 11, 0, 0),
        null);

    mvc.perform(post("/api/stock/getMaxProfit")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", CoreMatchers.is("stockPrices must contains at least two minutes' date.")));
  }

  @Test
  public void testOnlyOneStockPrice() throws Exception {
    MaxStockProfitRequest request = new MaxStockProfitRequest(1L, LocalDateTime.of(2021, 12, 1, 10, 0, 0), LocalDateTime.of(2021, 12, 1, 11, 0, 0),
        Arrays.asList(1));

    mvc.perform(post("/api/stock/getMaxProfit")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", CoreMatchers.is("stockPrices must contains at least two minutes' date.")));
  }


  @Test
  public void testGetMaxProfit() throws Exception {

    MaxStockProfitRequest request = new MaxStockProfitRequest(1L, LocalDateTime.of(2021, 12, 1, 10, 0, 0), LocalDateTime.of(2021, 12, 1, 11, 0, 0),
        Arrays.asList(10, 7, 5, 8, 11, 9));

    mvc.perform(post("/api/stock/getMaxProfit")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.maxProfit", CoreMatchers.is(6)))
        .andExpect(jsonPath("$.buyDateTime", CoreMatchers.is("2021-12-01T10:02:00")))
        .andExpect(jsonPath("$.sellDateTime", CoreMatchers.is("2021-12-01T10:04:00")))
        .andExpect(jsonPath("$.buyPrice", CoreMatchers.is(5)))
        .andExpect(jsonPath("$.sellPrice", CoreMatchers.is(11)));

    // More values can be asserted here.
  }

  @Test
  public void testStockPriceAlwaysDecrease() throws Exception {

    MaxStockProfitRequest request = new MaxStockProfitRequest(1L, LocalDateTime.of(2021, 12, 1, 10, 0, 0), LocalDateTime.of(2021, 12, 1, 11, 0, 0),
        Arrays.asList(6, 5, 4, 3, 2, 1));

    mvc.perform(post("/api/stock/getMaxProfit")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.maxProfit", CoreMatchers.is(-1)));
  }


  @Test
  public void testStockPriceWithZero() throws Exception {

    MaxStockProfitRequest request = new MaxStockProfitRequest(1L, LocalDateTime.of(2021, 12, 1, 10, 0, 0), LocalDateTime.of(2021, 12, 1, 11, 0, 0),
        Arrays.asList(6, 5, 4, 3, 2, 1, 0));

    mvc.perform(post("/api/stock/getMaxProfit")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.maxProfit", CoreMatchers.is(-1)));
  }
}
