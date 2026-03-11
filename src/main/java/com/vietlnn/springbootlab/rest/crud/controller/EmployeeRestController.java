package com.vietlnn.springbootlab.rest.crud.controller;

import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import com.vietlnn.springbootlab.rest.crud.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Overall flow:
 * Rest Controller <-> Service <-> DAO <-> Database
 * */
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeRestController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/employees")
  public List<Employee> getEmployees() {
    return employeeService.findAll();
  }

  @GetMapping("/employees/{id}")
  public Employee getEmployeeById(@PathVariable Integer id) {
    return employeeService.findById(id);
  }
}
