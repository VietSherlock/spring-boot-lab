package com.vietlnn.springbootlab.springboot.rest.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
