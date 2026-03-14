package com.vietlnn.springbootlab.rest.crud.service;

import com.vietlnn.springbootlab.rest.crud.dao.BaseDao;
import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import com.vietlnn.springbootlab.rest.crud.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final BaseDao<Employee, Integer> employeeDao;

  @Autowired
  public EmployeeServiceImpl(BaseDao<Employee, Integer> employeeBaseDao) {
    this.employeeDao = employeeBaseDao;
  }

  @Override
  public List<Employee> findAll() {
    return employeeDao.findAll();
  }

  @Override
  public Employee findById(int id) {

    // identify employee
    Optional<Employee> foundEmployee = employeeDao.findById(id);

    // return 404 if not found
    if (foundEmployee.isEmpty()) {
      throw new NotFoundException(String.format("Cannot found Employee with id '%s'", id));
    }

    // return 200 if found
    return foundEmployee.get();
  }

  @Transactional // let business layer to handle transaction
  @Override
  public Employee save(Employee employee) {
    return employeeDao.save(employee);
  }

  @Transactional
  @Override
  public void deleteById(int id) {
    employeeDao.deleteById(id);
  }
}
