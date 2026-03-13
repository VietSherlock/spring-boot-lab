package com.vietlnn.springbootlab.rest.crud.service;

import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  List<Employee> findAll();

  Optional<Employee> findById(int id);
}
