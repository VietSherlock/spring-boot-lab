package com.vietlnn.springbootlab.rest.crud.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

  @NotNull private String firstName;
  @NotNull private String lastName;
  @NotNull private String email;
}
