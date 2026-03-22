package com.vietlnn.springbootlab.springboot.rest.service;

import com.vietlnn.springbootlab.springboot.rest.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  List<Employee> findAll();

  Optional<Employee> findById(int id);

  /**
   * Return an employee with delivered ID if found.<br>
   * Otherwise, throw NotFoundException.
   *
   * @param id Employee's ID.
   * @return found employee.
   */
  Employee lookupById(int id);

  Employee save(Employee employee);

  //  Employee update(Employee employee);

  void deleteById(int id);
}
