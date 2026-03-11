package com.vietlnn.springbootlab.rest.crud.service;

import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import java.util.List;

public interface EmployeeService {

  List<Employee> findAll();

  Employee findById(int id);
}
