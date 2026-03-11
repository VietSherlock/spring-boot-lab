package com.vietlnn.springbootlab.rest.crud.service;

import com.vietlnn.springbootlab.rest.crud.dao.Dao;
import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final Dao<Employee> employeeDao;

  @Autowired
  public EmployeeServiceImpl(Dao<Employee> employeeDao) {
    this.employeeDao = employeeDao;
  }

  @Override
  public List<Employee> findAll() {
    return employeeDao.findAll();
  }

  @Override
  public Employee findById(int id) {
    return employeeDao.findById(id);
  }
}
