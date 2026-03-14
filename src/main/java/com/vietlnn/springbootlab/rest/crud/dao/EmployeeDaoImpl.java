package com.vietlnn.springbootlab.rest.crud.dao;

import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl extends AbstractJpaDao<Employee, Integer> implements EmployeeDao {

  public EmployeeDaoImpl() {
    super(Employee.class);
  }
}
