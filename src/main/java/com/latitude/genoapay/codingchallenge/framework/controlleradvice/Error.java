package com.latitude.genoapay.codingchallenge.framework.controlleradvice;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Error {

  private String message;

  public Error message(String message) {
    this.message = message;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
