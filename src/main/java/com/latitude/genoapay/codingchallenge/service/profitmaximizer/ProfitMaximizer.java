package com.latitude.genoapay.codingchallenge.service.profitmaximizer;

import com.latitude.genoapay.codingchallenge.controller.dto.MaxStockProfitRequest;
import com.latitude.genoapay.codingchallenge.controller.dto.MaxStockProfitResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProfitMaximizer {

  /**
   * This algorithm's space complexity is O(n), time complexity is O(n).
   * Can do two loops, with space complexity O(1), and time complexity O(n^2).
   */
  public MaxStockProfitResponse getMaxProfit(MaxStockProfitRequest request) {
    Integer[] stockPrice = request.getStockPrices().toArray(new Integer[0]);
    MaxStockProfitResponse transaction = new MaxStockProfitResponse();
    // Find the highest prices till the end of the day for each minute.
    Integer[] maxPriceTillEnd = new Integer[stockPrice.length];
    for (int i = stockPrice.length - 1; i >= 0; i--) {
      // no price at this minute, so no highest price.
      if (stockPrice[i] == null) {
        maxPriceTillEnd = null;
        break;
      }
      if (i == stockPrice.length - 1) {
        maxPriceTillEnd[i] = stockPrice[i];
      } else {
        maxPriceTillEnd[i] = Math.max(stockPrice[i], maxPriceTillEnd[i + 1]);
      }
    }

    // The stock price might decrease all the time, so start from Integer.MIN_VALUE
    int maxProfitValue = Integer.MIN_VALUE;
    int maxSellPrice = 0;
    int buyMinute = 0;
    // You can not buy at the last minute, thus no chance to sell.
    for (int buyTime = 0; buyTime < stockPrice.length - 1; buyTime++) {
      if(maxProfitValue < maxPriceTillEnd[buyTime + 1] - stockPrice[buyTime]) {
        maxProfitValue = maxPriceTillEnd[buyTime + 1] - stockPrice[buyTime];
        maxSellPrice = maxPriceTillEnd[buyTime + 1];
        buyMinute = buyTime;
      }
    }
    // find the sell time, by comparing the sell price and the stock price.
    int sellMinute = 0;
    for(int i = buyMinute + 1; i<stockPrice.length; i++) {
      if (Objects.equals(maxSellPrice, stockPrice[i])) {
          sellMinute = i;
      }
    }
    transaction.setMaxProfit(maxProfitValue);
    transaction.setBuyPrice(stockPrice[buyMinute]);
    transaction.setBuyDateTime(request.getStartDateTime().plusMinutes(buyMinute));
    transaction.setSellDateTime(request.getStartDateTime().plusMinutes(sellMinute));
    transaction.setSellPrice(stockPrice[sellMinute]);
    transaction.setRequest(request);
    return transaction;
  }

}
