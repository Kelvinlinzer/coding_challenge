package com.latitude.genoapay.codingchallenge.controller.dto;

import java.time.LocalDateTime;

public class MaxStockProfitResponse {

  private MaxStockProfitRequest request;

  private int buyPrice;
  private int sellPrice;
  private int maxProfit;
  private LocalDateTime buyDateTime;
  private LocalDateTime sellDateTime;

  public LocalDateTime getBuyDateTime() {
    return buyDateTime;
  }

  public void setBuyDateTime(LocalDateTime buyDateTime) {
    this.buyDateTime = buyDateTime;
  }

  public LocalDateTime getSellDateTime() {
    return sellDateTime;
  }

  public void setSellDateTime(LocalDateTime sellDateTime) {
    this.sellDateTime = sellDateTime;
  }

  public int getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(int buyPrice) {
    this.buyPrice = buyPrice;
  }

  public int getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(int sellPrice) {
    this.sellPrice = sellPrice;
  }

  public int getMaxProfit() {
    return maxProfit;
  }

  public void setMaxProfit(int maxProfit) {
    this.maxProfit = maxProfit;
  }

  public MaxStockProfitRequest getRequest() {
    return request;
  }

  public void setRequest(MaxStockProfitRequest request) {
    this.request = request;
  }
}
