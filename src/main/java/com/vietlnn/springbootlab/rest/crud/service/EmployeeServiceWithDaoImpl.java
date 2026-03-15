package com.vietlnn.springbootlab.rest.crud.service;

import com.vietlnn.springbootlab.rest.crud.dao.BaseDao;
import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import com.vietlnn.springbootlab.rest.crud.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** EmployeeService used JPA + Hibernate with DAO structural pattern */
@Service
public class EmployeeServiceWithDaoImpl implements EmployeeService {

  private final BaseDao<Employee, Integer> employeeDao;

  @Autowired
  public EmployeeServiceWithDaoImpl(BaseDao<Employee, Integer> employeeBaseDao) {
    this.employeeDao = employeeBaseDao;
  }

  @Override
  public List<Employee> findAll() {
    return employeeDao.findAll();
  }

  @Override
  public Optional<Employee> findById(int id) {
    return employeeDao.findById(id);
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

  // let business layer to handle transaction
  // -> required in JPA, but no needed in Spring Data JPA
  // (since JpaRepository provides this functionality)
  @Transactional
  @Override
  public Employee save(Employee employee) {
    return employeeDao.save(employee);
  }

  @Transactional
  public Employee update(Employee employee) {
    return employeeDao.update(employee);
  }

  @Transactional
  @Override
  public void deleteById(int id) {
    employeeDao.deleteById(id);
  }
}
