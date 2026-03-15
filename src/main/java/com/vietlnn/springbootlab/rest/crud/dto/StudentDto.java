package com.vietlnn.springbootlab.rest.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// POJO class or DTO (Data Transfer Object)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
  private String firstName;
  private String lastName;
}
