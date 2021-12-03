package com.latitude.genoapay.codingchallenge.framework.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

/**
 * Convert the exception into a body, so it's easier to check message in test code.
 * And some more customized exception handling can be done.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(value = { ResponseStatusException.class })
  public ResponseEntity<Error> handleConstraintViolationException(ResponseStatusException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new Error().message(exception.getReason()));
  }

}
