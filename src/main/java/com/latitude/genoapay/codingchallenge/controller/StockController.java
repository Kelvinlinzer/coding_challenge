package com.latitude.genoapay.codingchallenge.controller;

import com.latitude.genoapay.codingchallenge.controller.dto.MaxStockProfitRequest;
import com.latitude.genoapay.codingchallenge.controller.dto.MaxStockProfitResponse;
import com.latitude.genoapay.codingchallenge.service.profitmaximizer.ProfitMaximizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/stock")
public class StockController {

  private final ProfitMaximizer profitMaximizer;

  public StockController(ProfitMaximizer profitMaximizer) {
    this.profitMaximizer = profitMaximizer;
  }

  @PostMapping(value = "/getMaxProfit", consumes = "application/json", produces = "application/json")
  public ResponseEntity<MaxStockProfitResponse> getMaxProfit(@RequestBody MaxStockProfitRequest request) {

    if (request.getStartDateTime() == null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,"startDateTime must be provided");
    }

    if (CollectionUtils.isEmpty(request.getStockPrices()) || request.getStockPrices().size() < 2) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "stockPrices must contains at least two minutes' date.");
    }

    return ResponseEntity.ok(profitMaximizer.getMaxProfit(request));
  }
}
