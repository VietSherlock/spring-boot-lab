package com.vietlnn.springbootlab.rest.crud.controller;

import com.vietlnn.springbootlab.rest.crud.dto.EmployeeDto;
import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import com.vietlnn.springbootlab.rest.crud.mapper.EmployeeMapper;
import com.vietlnn.springbootlab.rest.crud.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Overall flow:
 * Rest Controller <-> Service <-> DAO <-> Database
 * */
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper;

  @Autowired
  public EmployeeRestController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
    this.employeeService = employeeService;
    this.employeeMapper = employeeMapper;
  }

  @GetMapping("/employees")
  public List<Employee> getEmployees() {
    return employeeService.findAll();
  }

  @GetMapping("/employees/{id}")
  public Employee getEmployeeById(@PathVariable Integer id) {
    return employeeService.findById(id);
  }

  @PostMapping(value = "/employees", consumes = "application/json", produces = "application/json")
  public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
    employeeService.save(employeeMapper.toEntity(employeeDto));
    return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
  }
}
