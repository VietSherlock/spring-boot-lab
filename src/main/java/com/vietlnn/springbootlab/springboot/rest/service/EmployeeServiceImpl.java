package com.vietlnn.springbootlab.springboot.rest.service;

import com.vietlnn.springbootlab.springboot.rest.entity.Employee;
import com.vietlnn.springbootlab.springboot.rest.exception.NotFoundException;
import com.vietlnn.springbootlab.springboot.rest.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/** EmployeeService used Spring Data JPA (JpaRepository) + Hibernate */
@Primary
@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Optional<Employee> findById(int id) {
    return employeeRepository.findById(id);
  }

  @Override
  public Employee lookupById(int id) {

    Optional<Employee> foundEmployee = findById(id);

    // return 404 if not found
    if (foundEmployee.isEmpty()) {
      throw new NotFoundException(String.format("Employee with id '%s' cannot be found.", id));
    }
    return foundEmployee.get();
  }

  /**
   * Handle both insert and update.
   *
   * <p>If id = null -> insert - entityManager.persist().<br>
   * Otherwise, update - entityManager.merge().
   *
   * @param employee Employee to be inserted/updated
   * @return Employee inserted/updated
   */
  @Override
  public Employee save(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public void deleteById(int id) {
    employeeRepository.deleteById(id);
  }
}
