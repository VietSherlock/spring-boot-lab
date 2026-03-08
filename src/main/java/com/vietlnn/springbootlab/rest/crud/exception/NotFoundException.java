package com.vietlnn.springbootlab.rest.crud.exception;

// custom exception for not found entity handling
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}
