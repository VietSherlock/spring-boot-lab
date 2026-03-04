package com.vietlnn.springbootlab.rest.crud.exception;

public class StudentNotFoundException extends RuntimeException {

  public StudentNotFoundException(String message) {
    super(message);
  }
}
