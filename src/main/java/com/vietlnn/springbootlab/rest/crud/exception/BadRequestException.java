package com.vietlnn.springbootlab.rest.crud.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
