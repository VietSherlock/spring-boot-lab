package com.vietlnn.springbootlab.springboot.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentMvc {

  private String firstName;
  private String lastName;
  private String country;
}
