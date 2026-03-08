package com.vietlnn.springbootlab.rest.crud.exception;

import com.vietlnn.springbootlab.rest.crud.pojo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// ControllerAdvice used to pre-processing request and post-handling response for REST services
// one of real use case for defining global rest ExceptionHandling
@ControllerAdvice
public class RestExceptionHandler {

  // ResponseEntity is a wrapper for HTTP response object to specify HTTP status code, headers, body
  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleException(NotFoundException exception) {
    ErrorResponse error =
        new ErrorResponse(
            HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
    // wrap body with a status code -> if not, 200 status code is returned
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleException(Exception exception) {
    ErrorResponse error =
        new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
